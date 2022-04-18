package com.cdf.mall.demo.decorator;

/**
 * @Description 具体的装饰器
 * 装饰器模式在不影响各个ConcreteComponent核心价值的同时，添加了他特有的装饰效果，具备非常好的通用性，这也是他存在的最大价值。
 * @Author hanyaguang
 * @Date 2022/3/24 16:07
 * @Version 1.0
 */
public class ConcreteDecorator extends Decorator{
    public ConcreteDecorator(MyComponent myComponent) {
        super(myComponent);
    }

    public void biu() {
        System.out.println("ready?go!");
        this.myComponent.biu();
    }

    public static void main(String[] args) {
        //使用装饰器
        MyComponent component = new ConcreteDecorator(new ConcretComponent());
        component.biu();
    }
}
