package com.cdf.mall.demo.strategy;

import com.cdf.mall.demo.strategy.enums.OrderType;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 策略的创建
 * @Author hanyaguang
 * @Date 2022/3/29 11:18
 * @Version 1.0
 */
public class DiscountStrategyFactory {
    private static final Map<OrderType, DiscountStrategy> strategies = new HashMap<>();

    static {
        strategies.put(OrderType.NORMAL, new NormalDiscountStrategy());
        strategies.put(OrderType.GROUPON, new GrouponDiscountStrategy());
        strategies.put(OrderType.PROMOTION, new PromotionDiscountStrategy());
    }


    public static DiscountStrategy getDiscountStrategy(OrderType type) {
        if (ObjectUtils.isEmpty(type)) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return strategies.get(type);
    }

}
