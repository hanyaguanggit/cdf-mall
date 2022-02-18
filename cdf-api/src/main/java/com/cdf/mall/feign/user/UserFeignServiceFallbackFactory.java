package com.cdf.mall.feign.user;

import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.commons.ResultCode;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description 查询文章
 * @Author hanyaguang
 * @Date 2021/10/26 10:56
 * @Version 1.0
 */
@Slf4j
@Component
public class UserFeignServiceFallbackFactory implements FallbackFactory<UserFeignService> {

    @Override
    public UserFeignService create(Throwable throwable) {

        log.error(this.getClass().getSimpleName() + "feign异常,{}",throwable.getMessage());

        return new UserFeignService() {

            @Override
            public CommonResult selectById(Integer id) {
                return CommonResult.failed(ResultCode.FAILED,"feign调用，网路请求超时！");
            }
        };
    }
}
