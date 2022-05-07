package com.cdf.mall.controller;

import com.cdf.mall.util.RedisOpsUtil;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.ObjectUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@RestController
public class KafkaController {
    private final static String TOPIC_NAME = "ecsTaskEvent_test";
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    @Resource()
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private RedisOpsUtil redisOpsUtil;


    @RequestMapping("/send")
    public void send(@RequestParam("topicName") String topicName, @RequestParam("name") String name, @RequestParam("message") String message) throws ExecutionException, InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, name, message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        SendResult<String, String> metadata = future.get();
        if (!ObjectUtils.isEmpty(metadata)) {
            logger.info("发送消息message：{}", metadata);
        }
    }

    /**
     * description: 定时发送更新缓存的消息，每5秒执行一次。
     * 定时任务更新缓存的逻辑：
     * 1、生产者一次性把所有消息队列推出后全部发送出去。
     * 2、消费者收到消息后循环处理集合中每个元素，到时间的进行更新。没有到时间的和执行失败的重新推入队列。
     * @Author: hanyaguang
     * @Date: 2022/1/17 10:33
     */
    //@Scheduled(cron = "*/5 * * * * ?")
    /*@RequestMapping("/send/message")
    public void updateCacheProducer() {
        long startTime=System.currentTimeMillis(); //获取开始时间
        logger.info("首页生效失效时间定时任务开始");
        Boolean lock = redisOpsUtil.redLock(RedisKeyConsts.INDEX_UPDATE_DATE, 1, 50);
        List<Object> messageList = new LinkedList<>();
        logger.info("更新首页定时任务成功获取redis锁");
        if(!lock){
            logger.info("更新首页锁被使用，等待中ing");
            return;
        }
        try {
            if(lock){
                long size = redisOpsUtil.lGetListSize(RedisKeyConsts.INDEX_UPDATE_DATE);
                for(int i = 0; i < size; i++){
                    Object message = redisOpsUtil.rPop(RedisKeyConsts.INDEX_UPDATE_DATE);
                    messageList.add(message);
                }
                ProducerRecord<String,String> record = new ProducerRecord<>(TOPIC_NAME,"updateCache", JSONObject.toJSONString(messageList));
                kafkaTemplate.send(record);
            }
        }catch (Exception e){
            logger.info("更新首页定时任务异常：", e);
        }finally {
            redisOpsUtil.deleteRedLock(RedisKeyConsts.INDEX_UPDATE_DATE);
            logger.info("更新首页定时任务释放redis锁,当前时间：{}", DateUtils.formatDate(new Date(),DateUtils.DATE_FORMAT_DEFAULT));
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        logger.info("使用时间: {}", (endTime-startTime) + "ms");
        logger.info("首页生效失效时间定时任务结束");
    }*/
}
