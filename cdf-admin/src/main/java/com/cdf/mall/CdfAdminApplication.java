package com.cdf.mall;

import com.cdf.mall.config.RedisTemplateConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//禁用mongodb的自动配置
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//禁用redis自动配置
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
//@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
public class CdfAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(CdfAdminApplication.class, args);
    }
}
