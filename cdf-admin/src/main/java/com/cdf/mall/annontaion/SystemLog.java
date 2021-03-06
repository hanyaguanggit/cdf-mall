package com.cdf.mall.annontaion;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    /**
     * 查询模块
     */
    String module() default "";

    /**
     * 查询模块名称
     */
    String methods() default "";

    /**
     * 查询的bean名称
     */
    String serviceClass() default "";

    /**
     * 查询单个详情的bean的方法
     */
    String queryMethod() default "";

    /**
     * 查询详情的参数类型
     */
    String parameterType() default "";

    /**
     * 从页面参数中解析出要查询的id，
     * 如域名修改中要从参数中获取customerDomainId的值进行查询
     */
    String parameterKey() default "";

    /**
     * 是否为批量类型操作
     */
    boolean paramIsArray() default false;

}
