package com.cdf.mall.kafka;

import com.cdf.mall.service.Producer;
import com.cdf.mall.util.httpclient.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * hyg
 * kafka 生产者发送消息抽象类
 * @param <T>
 * @param <S>
 */
public class AbstractKafkaProducer<T,S> implements Producer {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected String topic;

    public AbstractKafkaProducer(String topic) {
        this.topic = topic;
    }

    @Autowired
    protected KafkaTemplate kafkaTemplate;

    /**
     * 重新封装发送方法
     * @param event
     */
    @Override
    public void send(Object event) {
        if (this.logger.isDebugEnabled())
            this.logger.debug(">>> {}", event);
        if (event != null) {
            String msg = null;
            if (event instanceof String) {
                msg = (String)event;
            } else {
                msg = JsonUtil.toJson(event);
            }
            ListenableFuture<SendResult<T, S>> future = this.kafkaTemplate.send(this.topic, msg);
            future.addCallback(this::onSuccess, this::onFailed);
        }
    }

    public void send(String name, Object event) {
        if (this.logger.isDebugEnabled())
            this.logger.debug(">>> {} {}", name, event);
        if (event != null) {
            String msg = null;
            if (event instanceof String) {
                msg = (String)event;
            } else {
                StringBuilder buf = new StringBuilder();
                buf.append(name).append("||&&||");
                buf.append(JsonUtil.toJson(event));
                msg = buf.toString();
            }
            ListenableFuture<SendResult<T, S>> future = this.kafkaTemplate.send(this.topic, msg);
            future.addCallback(this::onSuccess, this::onFailed);
        }
    }

    public void send1(String name, Object event) {
        if (this.logger.isDebugEnabled())
            this.logger.debug(">>> {} {}", name, event);
        if (event != null) {
            String msg = null;
            if (event instanceof String) {
                msg = (String)event;
            } else {
                StringBuilder buf = new StringBuilder();
                buf.append(JsonUtil.toJson(event));
                msg = buf.toString();
            }
            ListenableFuture<SendResult<T, S>> future = this.kafkaTemplate.send(name, msg);
            future.addCallback(this::onSuccess, this::onFailed);
        }
    }

    protected void onSuccess(SendResult<T, S> sendResult) {
        if (this.logger.isDebugEnabled())
            this.logger.debug(">>> topic={},key={},value={}", new Object[] { sendResult.getProducerRecord().topic(), sendResult.getProducerRecord().key(), sendResult.getProducerRecord().value() });
    }

    protected void onFailed(Throwable throwable) {
        this.logger.error(">>> error:{}", throwable.getMessage());
        this.logger.error(throwable.getMessage(), throwable);
    }
}
