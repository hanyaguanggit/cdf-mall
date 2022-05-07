package com.cdf.mall.kafka;

import com.cdf.mall.service.OrderExchangeEventProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * hyg
 *
 *  and !'${mq.producer.synorderevent.queue:}'.isEmpty()
 * 订单变更
 */
@Component
@ConditionalOnExpression("'${mq.kafka.enabled}' == 'true'")
public class KafkaOrderExchangeEventProducer extends AbstractKafkaProducer implements OrderExchangeEventProducer {
    public KafkaOrderExchangeEventProducer(@Value("${mq.producer.synorderevent.queue}") String topic) {
        super(topic);
    }
}
