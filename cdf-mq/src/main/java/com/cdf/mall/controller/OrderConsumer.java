package com.cdf.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.cdf.mall.common.CommonResult;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

//@Component
public class OrderConsumer {

    Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    //@Autowired
    //CsOrderFeignService csOrderFeignService;

    /**
     * description:
     * @Param:
     * @Return:
     * @Author: yaoguanglei
     * @Date: 2021/11/8 15:31
     */
    @KafkaListener(topics = "order-topic",groupId = "cdfoder",concurrency="5")
    public void listenReplicated(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
       /* CsOrder vo = JSONObject.parseObject(value, CsOrder.class);
        CommonResult result = csOrderFeignService.insetOrder(vo);
        if((int)result.getData() > 0) {
            logger.info("同步订单数据成功：订单id={}", vo.getOrderno());
            //手动提交offset
            ack.acknowledge();
        }else {
            logger.info("同步订单数据失败，重新进入消息队列：订单id={}", vo.getOrderno());
        }*/
    }

}
