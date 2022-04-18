package com.cdf.mall.demo.template;

/**
 * @Description 具体类
 * @Author hanyaguang
 * @Date 2022/3/28 17:41
 * @Version 1.0
 */
public class ConcreteClass1 extends AbstractClass{
    @Override
    protected void method2() {
        System.out.println("进入method2");
    }

    @Override
    protected void method1() {
        System.out.println("进入method1");
    }

    public static void main(String[] args) {
        AbstractClass demo = new ConcreteClass1();
        demo.templateMethod();
    }
}
