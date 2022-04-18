package com.cdf.mall.demo.observer;

import com.cdf.mall.demo.observer.source.Subscribe;
import com.cdf.mall.model.second.CdfUser;
import com.cdf.mall.service.second.UserService;

/**
 * @Description 注册后通知行为
 * @Author hanyaguang
 * @Date 2022/3/25 18:12
 * @Version 1.0
 */
public class RegNotificationObserver {
    private UserService userService;

    @Subscribe
    public void handleRegSuccess(Long userId) {
        //发券逻辑
        //userService.insert(new CdfUser());
    }
}
