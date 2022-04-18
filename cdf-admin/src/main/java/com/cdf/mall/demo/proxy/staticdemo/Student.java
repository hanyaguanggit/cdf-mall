package com.cdf.mall.demo.proxy.staticdemo;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/3/24 14:37
 * @Version 1.0
 */
public class Student implements IPserson {
    private static String name = "hanyaguang同学";


    @Override
    public void read() {
        System.out.println(name+"在学习。。。");
    }
}
