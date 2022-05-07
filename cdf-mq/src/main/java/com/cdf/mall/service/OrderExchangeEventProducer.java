package com.cdf.mall.service;

/**
 * hyg
 * 订单变更事件生产者
 */
public interface OrderExchangeEventProducer extends Producer{
    void send(String paramString, Object paramObject);
}
