package com.cdf.mall.rocketmq;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 开启定时任务
 * @Author hanyaguang
 * @Date 2022/5/24 15:24
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({TaskConfiguration.class})
public @interface EnableTask {
}
