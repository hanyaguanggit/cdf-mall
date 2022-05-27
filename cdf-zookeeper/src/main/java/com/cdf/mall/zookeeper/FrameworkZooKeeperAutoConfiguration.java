package com.cdf.mall.zookeeper;

import com.cdf.mall.zookeeper.lock.LockFactory;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Description zookeeper自动配置类
 * @Author hanyaguang
 * @Date 2022/5/26 11:30
 * @Version 1.0
 */
@Configuration
public class FrameworkZooKeeperAutoConfiguration {
    @Autowired
    private Environment environment;
    @Value("${spring.application.name}")
    private String applicationName;

    public FrameworkZooKeeperAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public CuratorFramework curatorFramework() {
        String zooKeeperServers = null;
        if (this.environment.getActiveProfiles()[0].equals("dev")) {
            zooKeeperServers = "10.197.18.6:2181";
        }

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3, 30000);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zooKeeperServers, retryPolicy);
        curatorFramework.start();
        return curatorFramework;
    }

    @Bean({"zookeeperLockFactory"})
    public LockFactory lockFactory(CuratorFramework curatorFramework) {
        return new LockFactory(curatorFramework, this.applicationName);
    }
}
