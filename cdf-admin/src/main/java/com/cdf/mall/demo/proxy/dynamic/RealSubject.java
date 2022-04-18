package com.cdf.mall.demo.proxy.dynamic;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/3/24 15:02
 * @Version 1.0
 */
public class RealSubject implements Subject {
    private String name = "hyg";

    @Override
    public void visit() {
        System.out.println(name+"在学习动态代理。。");
    }
}
