package com.cdf.mall.kafka;

import com.cdf.mall.service.OrderExchangeEventProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * @Description 生产者
 * @Author zhanglinggao
 * @Date 2021/11/4 13:41
 * @Version 1.0
 */
@Component
@ConditionalOnExpression("'${mq.kafka.enabled}' == 'true' and !'${mq.producer.synorderevent.queue:}'.isEmpty()")
public class KafkaExchangeEventProducer extends AbstractKafkaProducer implements OrderExchangeEventProducer {
    public KafkaExchangeEventProducer(@Value("${mq.producer.synorderevent.queue}") String topic) {
        super(topic);
    }
}
