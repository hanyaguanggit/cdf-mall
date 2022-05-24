package com.cdf.mall.rocketmq;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 执行任务
 * @Author hanyaguang
 * @Date 2022/5/24 15:50
 * @Version 1.0
 */
@FeignClient("system-zerotask")
public interface TaskFacade {
    @RequestMapping(
            value = {"/task/schedule"},
            method = {RequestMethod.POST}
    )
    void schedule(@RequestBody OnceTask task);
}
