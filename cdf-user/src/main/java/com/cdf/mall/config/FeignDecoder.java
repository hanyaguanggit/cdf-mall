package com.cdf.mall.config;

import com.alibaba.fastjson.JSON;
import com.cdf.mall.exception.biz.BizException;
import com.cdf.mall.exception.global.ExceptionObject;
import com.cdf.mall.util.ErrorUtil;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description feign调用异常自定义
 * @Author hanyaguang
 * @Date 2022/5/27 15:30
 * @Version 1.0
 */
public class FeignDecoder extends ErrorDecoder.Default {
    private static final Logger log = LoggerFactory.getLogger(FeignDecoder.class);

    public FeignDecoder() {
    }

    public Exception decode(String methodKey, Response response) {
        try {
           // String targetMsg = null;
            String body = Util.toString(response.body().asReader());
            log.debug("body:::{}", body);
            ExceptionObject eo = JSON.parseObject(body, ExceptionObject.class);
            Integer type = eo.getType();
            if (type != 1) {
                RuntimeException e = new RuntimeException(eo.getName());
                return e;
            } else {
                return BizException.fromJSON(eo.getContent());
            }
        } catch (Exception var8) {
            log.error("RPC Exception:{}", ErrorUtil.stackTraceToString(var8));
            return super.decode(methodKey, response);
        }
    }
}
