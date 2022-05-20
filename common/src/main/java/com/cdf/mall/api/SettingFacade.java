package com.cdf.mall.api;

import com.cdf.mall.bean.SystemSetting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 示例  查询数据库系统参数
 * @Author hanyaguang
 * @Date 2022/5/20 14:53
 * @Version 1.0
 */
@FeignClient("${service.feign.shop-common.name:shop-common}")
public interface SettingFacade {
  //  @ApiOperation("激活地址")
    @GetMapping({"/setting/findById"})
    SystemSetting findById(@RequestParam("id") String id);
}
