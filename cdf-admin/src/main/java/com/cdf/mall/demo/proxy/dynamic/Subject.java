package com.cdf.mall.demo.proxy.dynamic;

/**
 * @Description
 *   动态代理实现步骤：
 *   1、编写一个委托类的接口，即静态代理的（IPserson接口）
 *   2、实现一个真正的委托类，即静态代理的（RealSubject类）
 *   3、创建一个动态代理类，实现InvocationHandler 接口，并重写该invoke方法
 *   4、在测试类中，生成动态代理的对象
 * @Author hanyaguang
 * @Date 2022/3/24 14:53
 * @Version 1.0
 */
public interface Subject {
   void visit();
}
