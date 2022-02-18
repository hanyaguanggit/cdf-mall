package com.cdf.mall.feign.article;

import com.cdf.mall.commons.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * description: 文章模块
 * @Param:
 * @Return:
 * @Author: hanyaguang
 * @Date: 2021/10/26 10:59
 */
@FeignClient(value = "cdf-admin", path = "/cdf/admin", contextId="select-cdf-admin", fallbackFactory = ArticleFeignServiceFallbackFactory.class)
public interface ArticleFeignService {

    @GetMapping("/select/article")
    CommonResult selectById(@RequestParam("id") Integer id);


}
