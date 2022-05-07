package com.cdf.mall.service;

/**
 * hyg
 *
 */
public interface Producer {
    void send(Object paramObject);
    void send(String name, Object message);
}
