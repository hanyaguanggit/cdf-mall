package com.cdf.mall.feign.user;

import com.cdf.mall.commons.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * description: 用户模块
 * @Param:
 * @Return:
 * @Author: hanyaguang
 * @Date: 2021/10/26 10:59
 */
@FeignClient(value = "cdf-user", path = "/cdf/user", contextId="select-cdf-user", fallbackFactory = UserFeignServiceFallbackFactory.class)
public interface UserFeignService {

    @GetMapping("/{id}")
    CommonResult selectById(@PathVariable Integer id);

}
