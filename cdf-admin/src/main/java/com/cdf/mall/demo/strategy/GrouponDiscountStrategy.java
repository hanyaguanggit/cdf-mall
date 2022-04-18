package com.cdf.mall.demo.strategy;
import com.cdf.mall.demo.strategy.bean.Order;

/**
 * @Description 团购优惠策略
 * @Author hanyaguang
 * @Date 2022/3/29 11:15
 * @Version 1.0
 */
public class GrouponDiscountStrategy implements DiscountStrategy{
    @Override
    public double calDiscount(Order order) {
        System.out.println("执行团购优惠策略");
        return 155;
    }
}
