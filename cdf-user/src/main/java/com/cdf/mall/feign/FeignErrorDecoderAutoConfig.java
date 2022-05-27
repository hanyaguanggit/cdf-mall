package com.cdf.mall.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

/**
 * @Description 自定义feign处理配置类前置
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
