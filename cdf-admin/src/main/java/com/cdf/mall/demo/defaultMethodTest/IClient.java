package com.cdf.mall.demo.defaultMethodTest;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/4/2 10:14
 * @Version 1.0
 */
public interface IClient {
    abstract void getClient();

    default void getMyClient(){
        System.out.println("进入IClient接口的getMyClient（）方法");
    };
}
