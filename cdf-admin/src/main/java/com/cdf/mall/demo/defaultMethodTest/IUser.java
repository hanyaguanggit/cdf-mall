package com.cdf.mall.demo.defaultMethodTest;

public interface IUser {
    default void getOrder(){
        System.out.println("进入IUser接口中的getOrder（）方法");
    };
    default void getUser(){
        System.out.println("进入IUser中的getUser（）方法");
    };
}
