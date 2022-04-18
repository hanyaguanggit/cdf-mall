package com.cdf.mall.demo.observer;

import com.cdf.mall.demo.observer.source.Subscribe;
import com.cdf.mall.model.second.CdfUser;
import com.cdf.mall.model.second.CdfUserExample;
import com.cdf.mall.service.second.UserService;

/**
 * @Description 注册后发券行为
 * @Author hanyaguang
 * @Date 2022/3/25 18:09
 * @Version 1.0
 */
public class RegPromotionObserver {
    //
    private UserService userService; // 依赖注入

    @Subscribe
    public void handleRegSuccess(Long userId) {
        //
        userService.updateByExample(new CdfUser(),new CdfUserExample());
    }

}
