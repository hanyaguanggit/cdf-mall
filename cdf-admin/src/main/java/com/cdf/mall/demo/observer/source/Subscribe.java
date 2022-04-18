package com.cdf.mall.demo.observer.source;

import rx.annotations.Beta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 用于标明观察者中的哪个函数可以接收消息
 * @Author hanyaguang
 * @Date 2022/3/25 17:02
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Beta
public @interface Subscribe {
}
