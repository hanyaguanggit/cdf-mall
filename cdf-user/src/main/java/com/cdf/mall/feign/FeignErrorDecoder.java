package com.cdf.mall.feign;

import com.alibaba.fastjson.JSON;
import com.cdf.mall.exception.biz.BizException;
import com.cdf.mall.exception.global.ExceptionObject;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import feign.Util;
/**
 * @Description feign调用异常处理配置类
 * @Author hanyaguang
 * @Date 2022/5/16 15:40
 * @Version 1.0
 */
@Configuration
public class FeignErrorDecoder extends ErrorDecoder.Default {
    private static final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);

    public FeignErrorDecoder() {
    }

    public Exception decode(String methodKey, Response response) {
        try {
            String targetMsg = null;
            String body = Util.toString(response.body().asReader());
            log.error("body:::{}", body);
            ExceptionObject eo =  JSON.parseObject(body, ExceptionObject.class);
            Integer type = eo.getType();
            if (type != 1) {
                RuntimeException e = new RuntimeException(eo.getName());
                return e;
            } else {
                BizException exception = BizException.fromJSON(eo.getContent());
                return exception;
            }
        } catch (Exception var8) {
            log.error(var8.getMessage(), var8);
            return super.decode(methodKey, response);
        }
    }
}
