package com.cdf.mall.demo.proxy.staticdemo;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/3/24 14:21
 * @Version 1.0
 */
public class Person implements IPserson {
    @Override
    public void read() {
        System.out.println("hyg在学习代理模式！");
    }
}
