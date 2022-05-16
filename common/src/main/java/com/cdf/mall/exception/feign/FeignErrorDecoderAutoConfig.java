package com.cdf.mall.exception.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/5/16 15:36
 * @Version 1.0
 */
@ConditionalOnProperty(
        prefix = "service.error.feign",
        value = {"enabled"},
        matchIfMissing = false
)
@Import({FeignErrorDecoder.class})
public class FeignErrorDecoderAutoConfig{
    public FeignErrorDecoderAutoConfig() {
    }
}
