package com.cdf.mall.feign.article;

import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.commons.ResultCode;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 查询文章
 * @Author hanyaguang
 * @Date 2021/10/26 10:56
 * @Version 1.0
 */
@Slf4j
@Component
public class ArticleFeignServiceFallbackFactory implements FallbackFactory<ArticleFeignService> {

    @Override
    public ArticleFeignService create(Throwable throwable) {

        log.error(this.getClass().getSimpleName() + "feign异常,{}",throwable.getMessage());

        return new ArticleFeignService() {

            @Override
            public CommonResult selectById(Integer id) {
                return CommonResult.failed(ResultCode.FAILED,"feign调用，网路请求超时！");
            }
        };
    }
}
