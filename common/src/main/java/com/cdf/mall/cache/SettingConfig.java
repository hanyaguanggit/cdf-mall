package com.cdf.mall.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @Description 系统参数配置类
 * @Author hanyaguang
 * @Date 2022/5/20 15:10
 * @Version 1.0
 */
public class SettingConfig {
    public SettingConfig() {
    }

    @Bean
    public SettingManger settingManger() {
        return new SettingManger();
    }

    @Bean
    public SystemSettingCache systemSettingCache() {
        return new SystemSettingCache();
    }

    @Bean(name = "simpleRetryTemplate")
    public RetryTemplate simpleRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy());//设置重试次数，默认3次
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(3000);//初始休眠时间，默认100毫秒
        backOffPolicy.setMultiplier(2);//指定乘数，当前休眠时间*multiplier即为下一次的休眠时间；
        backOffPolicy.setMaxInterval(15000);//指定最大休眠时间，默认30秒，避免multiplier过大引起无限期等待。
        retryTemplate.setBackOffPolicy(backOffPolicy);
        return retryTemplate;
    }
}
