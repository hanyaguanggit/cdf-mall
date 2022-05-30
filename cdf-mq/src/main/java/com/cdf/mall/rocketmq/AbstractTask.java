package com.cdf.mall.rocketmq;

import com.cdf.mall.exception.biz.BizException;
import com.cdf.mall.exception.biz.ErrorParam;
import com.cdf.mall.util.JsonUtils;
import com.rabbitmq.client.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.SmartLifecycle;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Description rocketMq消息发送抽象类，SmartLifecycle负责自动执行start方法，ChannelAwareMessageListener负责监听消息。
 * @Author hanyaguang
 * @Date 2022/5/30 16:43
 * @Version 1.0
 */
public abstract class AbstractTask implements SmartLifecycle, ChannelAwareMessageListener {
    private static final Logger log = LoggerFactory.getLogger(AbstractTask.class);
    @Qualifier("ConnectionFactoryUser")
    @Autowired
    private ConnectionFactory connectionFactory;
    @Qualifier("rabbitAdminUser")
    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private CronTaskDao cronTaskDao;
    private SimpleMessageListenerContainer messageListenerContainer;

    public AbstractTask() {
    }

    public abstract String getType();

    public abstract boolean isProcessed(Task t);

    public abstract void invoke(Task t);

    public void start() {
        try {
            DirectExchange exchange = new DirectExchange(this.getType());
            Queue queue = new Queue(this.getType());
            this.rabbitAdmin.declareQueue(queue);
            this.rabbitAdmin.declareExchange(exchange);
            Binding binding = BindingBuilder.bind(queue).to(exchange).with(this.getType());
            this.rabbitAdmin.declareBinding(binding);
            this.messageListenerContainer = new SimpleMessageListenerContainer(this.connectionFactory);
            this.messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
            this.messageListenerContainer.setQueueNames(new String[]{queue.getName()});
            this.messageListenerContainer.setMessageListener(this);
            this.messageListenerContainer.setDeclarationRetries(5);
            this.messageListenerContainer.setRetryDeclarationInterval(2000L);
            this.messageListenerContainer.setRecoveryInterval(2000L);
            this.messageListenerContainer.start();
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public void stop() {
        this.messageListenerContainer.stop();
    }

    public boolean isRunning() {
        return this.messageListenerContainer == null ? false : this.messageListenerContainer.isRunning();
    }

    public void onMessage(Message message, Channel channel) throws Exception {
        log.debug("Message Received: {}", message.toString());
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        Task task = (Task) JsonUtils.toBean(body, Task.class);
        if (task != null) {
            DistributedLock distributedLock = DistributedLockFactory.create(task.getId());
            if (!distributedLock.lock()) {
                log.warn("There is already a task[{}] with lock[{}] being executed", task.getName(), task.getId());
            } else {
                try {
                    if (!this.isProcessed(task)) {
                        long startTime = System.currentTimeMillis();
                        this.invoke(task);
                        long endTime = System.currentTimeMillis();
                        long costTime = endTime - startTime;
                        TaskMetrics.duration(task.getName(), costTime);
                    }

                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (task instanceof CronTask) {
                        CronTask crontASK = (CronTask)task;
                        Date nextTriggerTime = this.nextTriggerTime((CronTask)task);
                        this.cronTaskDao.success(task, nextTriggerTime);
                    } else {
                        OnceTask onceTask = (OnceTask)task;
                        if (onceTask.getType() == 0) {
                            this.taskDao.delete(onceTask);
                        } else {
                            this.taskDao.update(onceTask);
                        }
                    }
                } catch (BizException var16) {
                    ErrorParam errorParam = null;
                    ErrorParam[] var8 = var16.getErrorParams();
                    int var9 = var8.length;

                    for(int var10 = 0; var10 < var9; ++var10) {
                        ErrorParam errorParam1 = var8[var10];
                        if ("error".equals(errorParam1.getName())) {
                            errorParam = errorParam1;
                            break;
                        }
                    }

                    if (errorParam != null) {
                        log.error("invoke task error", var16);
                        TaskMetrics.failed(task.getName());
                        if (task instanceof CronTask) {
                            this.cronTaskDao.error(task, errorParam.getValue());
                        } else {
                            this.taskDao.error(task, errorParam.getValue());
                        }
                    } else {
                        log.error("invoke task error", var16);
                        TaskMetrics.failed(task.getName());
                        if (task instanceof CronTask) {
                            this.cronTaskDao.error(task, this.stackTraceToString(var16));
                        } else {
                            this.taskDao.error(task, this.stackTraceToString(var16));
                        }
                    }
                } catch (Exception var17) {
                    log.error("invoke task error", var17);
                    TaskMetrics.failed(task.getName());
                    if (task instanceof CronTask) {
                        this.cronTaskDao.error(task, this.stackTraceToString(var17));
                    } else {
                        this.taskDao.error(task, this.stackTraceToString(var17));
                    }
                } finally {
                    distributedLock.unlock();
                }

                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        }
    }

    public String stackTraceToString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public Date nextTriggerTime(CronTask cronTask) {
        CronTrigger cronTrigger = new CronTrigger(cronTask.getCron(), TimeZone.getTimeZone(cronTask.getTimeZone()));
        Date lastTime = cronTask.getNextTriggerTime();
        TriggerContext triggerContext = new SimpleTriggerContext(lastTime, lastTime, lastTime);
        return cronTrigger.nextExecutionTime(triggerContext);
    }
}
