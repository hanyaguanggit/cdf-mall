package com.cdf.mall.demo.template;

/**
 * @Description 模板模式  示例
 * @Author hanyaguang
 * @Date 2022/3/28 17:39
 * @Version 1.0
 */
public abstract class AbstractClass {
    public final void templateMethod() {
        System.out.println("进入模板方法");
        method1();
        method2();
    }

    protected abstract void method2();

    protected abstract void method1();
}
