package com.cdf.mall.cache;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 其他服务若是想在启动的时候加载系统参数缓存，可以在启动类上加入这个注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SettingConfig.class})
public @interface EnableSettingManager {
}
