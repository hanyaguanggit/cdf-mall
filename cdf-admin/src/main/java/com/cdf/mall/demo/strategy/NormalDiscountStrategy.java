package com.cdf.mall.demo.strategy;

import com.cdf.mall.demo.strategy.bean.Order;
import com.cdf.mall.model.second.CdfUser;

/**
 * @Description 一般优惠策略
 * @Author hanyaguang
 * @Date 2022/3/29 11:14
 * @Version 1.0
 */
public class NormalDiscountStrategy implements DiscountStrategy{
    @Override
    public double calDiscount(Order order) {
        System.out.println("执行一般优惠策略");
        return 122;
    }
}
