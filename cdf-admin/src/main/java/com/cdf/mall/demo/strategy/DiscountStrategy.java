package com.cdf.mall.demo.strategy;

import com.cdf.mall.demo.strategy.bean.Order;

/**
 * @Description 策略定义
 * @Author hanyaguang
 * @Date 2022/3/29 11:13
 * @Version 1.0
 */
public interface DiscountStrategy {
    double calDiscount(Order order);
}
