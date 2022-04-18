package com.cdf.mall.demo.decorator;

/**
 * @Description 抽象的装饰
 * @Author hanyaguang
 * @Date 2022/3/24 16:05
 * @Version 1.0
 */
public class Decorator implements MyComponent {

    public MyComponent myComponent;
    public Decorator(MyComponent component) {

        this.myComponent = component;
    }
    @Override
    public void biu() {
      this.myComponent.biu();
    }



}
