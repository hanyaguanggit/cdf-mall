package com.cdf.mall.demo.strategy.enums;

/**
 * @Description 订单优惠类型枚举
 * @Author hanyaguang
 * @Date 2022/3/29 11:33
 * @Version 1.0
 */
public enum  OrderType {
    NORMAL(1,"一般优惠"),
    GROUPON(2,"团购优惠"),
    PROMOTION(3,"优惠券优惠");


    private Integer value;
    private String dsc;

    OrderType(Integer value, String dsc) {
        this.value = value;
        this.dsc = dsc;
    }

    public Integer value() {
        return value;
    }

    public String dsc() {
        return dsc;
    }
}
