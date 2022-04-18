package com.cdf.mall.demo.proxy.dynamic;

import com.cdf.mall.demo.proxy.staticdemo.IPserson;
import com.cdf.mall.demo.proxy.staticdemo.Person;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 *  动态代理实现步骤：
 *  *   1、编写一个委托类的接口，即静态代理的（IPserson接口）
 *  *   2、实现一个真正的委托类，即静态代理的（Person类）
 *  *   3、创建一个动态代理类，实现InvocationHandler 接口，并重写该invoke方法
 *  *   4、在测试类中，生成动态代理的对象
 * @Author hanyaguang
 * @Date 2022/3/24 15:05
 * @Version 1.0
 */
public class DynamicProxy implements InvocationHandler {
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object, args);
        return result;
    }

    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        DynamicProxy proxy = new DynamicProxy(realSubject);
        ClassLoader classLoader = realSubject.getClass().getClassLoader();

       //创建动态代理的对象，需要借助 Proxy.newProxyInstance。该方法的三个参数分别是：
        //    ClassLoader loader表示当前使用到的appClassloader。
        //    Class<?>[] interfaces表示目标对象实现的一组接口。
        //    InvocationHandler h表示当前的InvocationHandler实现实例对象。
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, new  Class[]{Subject.class}, proxy);
        subject.visit();

        //test2
        IPserson pserson = new Person();
        DynamicProxy proxy2 = new DynamicProxy(pserson);
        ClassLoader classLoader2 = pserson.getClass().getClassLoader();
        IPserson iPserson = (IPserson) Proxy.newProxyInstance(classLoader2, new  Class[]{IPserson.class}, proxy2);
        iPserson.read();
    }
}