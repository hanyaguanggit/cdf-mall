package com.cdf.mall.advice;

import brave.Tracer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdf.mall.bean.Result;
import com.cdf.mall.common.CommonErrorCode;
import com.cdf.mall.exception.biz.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 控制器层异常拦截器
 * @Author hanyaguang
 * @Date 2022/5/16 15:51
 * @Version 1.0
 */
//@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

    @Autowired
    private Tracer tracer;

    public ControllerAdvice() {
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ModelAndView errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        log.error("error:", e);
        Result r = new Result();
        r.setRequestId(this.tracer.currentSpan().context().traceIdString());
        r.setCode(CommonErrorCode.UNKNOWN_ERROR.getCode());
        r.setMessage(CommonErrorCode.UNKNOWN_ERROR.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(r));
        return null;
    }

    @ResponseBody
    @ExceptionHandler({BizException.class})
    public ModelAndView handleGlobalException(HttpServletResponse response, BizException globalException) throws IOException {
        Result r = new Result();
        r.setRequestId(this.tracer.currentSpan().context().traceIdString());
        JSONObject jsonObject = null;
        if (globalException.getJson() == null) {
            jsonObject = JSON.parseObject(BizException.toJSON(globalException));
        } else {
            jsonObject = JSON.parseObject(globalException.getJson());
        }

        if (jsonObject == null) {
            r.setRequestId(this.tracer.currentSpan().context().traceIdString());
            r.setCode(CommonErrorCode.UNKNOWN_ERROR.getCode());
            r.setMessage(CommonErrorCode.UNKNOWN_ERROR.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(r));
            return null;
        } else {
            r.setCode(jsonObject.getInteger("code"));
            r.setMessage(jsonObject.getString("message"));
            r.setAddition(jsonObject.getJSONObject("addition"));
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(r));
            return null;
        }
    }
}
