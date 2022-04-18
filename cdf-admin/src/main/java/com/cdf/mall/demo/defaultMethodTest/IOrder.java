package com.cdf.mall.demo.defaultMethodTest;

public interface IOrder {
    default void  getOrder(){
        System.out.println("进入IOrder中的getOrder（）方法。");
    };
}
