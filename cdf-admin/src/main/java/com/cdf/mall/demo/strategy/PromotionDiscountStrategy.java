package com.cdf.mall.demo.strategy;
import com.cdf.mall.demo.strategy.bean.Order;

/**
 * @Description 优惠券策略
 * @Author hanyaguang
 * @Date 2022/3/29 11:16
 * @Version 1.0
 */
public class PromotionDiscountStrategy implements DiscountStrategy{
    @Override
    public double calDiscount(Order order) {
        System.out.println("执行优惠券优惠策略");
        return 10;
    }
}
