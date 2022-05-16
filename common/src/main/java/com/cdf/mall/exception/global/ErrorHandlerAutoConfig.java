package com.cdf.mall.exception.global;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

/**
 * @Description 全局异常处理
 * @Author hanyaguang
 * @Date 2022/5/16 15:46
 * @Version 1.0
 */
@ConditionalOnProperty(
        prefix = "service.error.handler",
        value = {"enabled"},
        matchIfMissing = false
)
@Import({GlobalExceptionHandler.class})
public class ErrorHandlerAutoConfig {

}
