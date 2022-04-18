package com.cdf.mall.demo.decorator;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/3/24 16:02
 * @Version 1.0
 */
public class ConcretComponent implements MyComponent {

    //这是原本的功能
    @Override
    public void biu() {
        System.out.println("hyg在学习装饰器模式");
    }
}
