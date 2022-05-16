package com.cdf.mall.exception.global;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.cdf.mall.common.CommonErrorCode;
import com.cdf.mall.exception.biz.BizException;
import com.cdf.mall.exception.biz.ErrorParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Description 全局异常处理
 * @Author hanyaguang
 * @Date 2022/5/16 15:14
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String systemExceptionHandler(Exception exception, HttpServletRequest request) {
        log.error("URL:{} ,系统异常", request.getRequestURI(), exception);
        ExceptionObject eo = new ExceptionObject();
        eo.setCla(exception.getClass().getName());
        eo.setName(exception.getMessage());
        eo.setType(0);
        return JSON.toJSONString(eo);
    }

    @ExceptionHandler({BizException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String baseExceptionHandler(BizException baseException, HttpServletRequest request) {
        log.warn("URL:{} ,业务异常", request.getRequestURI(), baseException);
        ExceptionObject eo = new ExceptionObject();
        eo.setCla(baseException.getClass().getName());
        eo.setName("GlobalException");
        eo.setType(1);
        eo.setContent(BizException.toJSON(baseException));
        return JSON.toJSONString(eo);
    }

    @ExceptionHandler({UndeclaredThrowableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String sentinelExceptionHandler(UndeclaredThrowableException baseException, HttpServletRequest request) {
        log.warn("URL:{} ,业务异常", request.getRequestURI(), baseException);
        Throwable throwable = baseException.getUndeclaredThrowable();
        if (throwable instanceof BlockException) {
            BizException bizException = new BizException(CommonErrorCode.ERROR_BLOCK, new ErrorParam[]{new ErrorParam("message", "请求人数过多请稍后再试")});
            ExceptionObject eo = new ExceptionObject();
            eo.setCla(bizException.getClass().getName());
            eo.setName("GlobalException");
            eo.setType(1);
            eo.setContent(BizException.toJSON(bizException));
            return JSON.toJSONString(eo);
        } else {
            ExceptionObject eo = new ExceptionObject();
            eo.setCla(throwable.getClass().getName());
            eo.setName(throwable.getMessage());
            eo.setType(0);
            return JSON.toJSONString(eo);
        }
    }
}
