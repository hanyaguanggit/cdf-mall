package com.cdf.mall.demo.strategy;

import com.cdf.mall.demo.strategy.bean.Order;
import com.cdf.mall.demo.strategy.enums.OrderType;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/3/29 11:38
 * @Version 1.0
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestStrategy {
    public static void main(String[] args) {
        Order order = new Order();
       // order.setType(OrderType.NORMAL);//一般优惠
       // order.setType(OrderType.GROUPON);//团购
        order.setType(OrderType.PROMOTION);//优惠券
        OrderType orderType = order.getType();
        DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(orderType);
        double money = discountStrategy.calDiscount(order);
        System.out.println("优惠后的金额="+money);
    }

}

