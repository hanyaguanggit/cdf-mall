package com.cdf.mall.demo.strategy.bean;

import com.cdf.mall.demo.strategy.enums.OrderType;
import lombok.Data;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/3/29 11:20
 * @Version 1.0
 */
@Data
public class Order {
    //订单类型
    private OrderType type;
}
