package com.cdf.mall.zookeeper.leader;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author hanyaguang
 * @Date 2022/5/26 10:55
 * @Version 1.0
 */
@Configuration
public class LeaderConfiguration {
    public LeaderConfiguration() {
    }

    @Bean
    public LeaderAspect leaderAspect(CuratorFramework curatorFramework) {
        return new LeaderAspect(curatorFramework);
    }
}
