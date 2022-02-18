package com.cdf.mall.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @Description mongo从库配置类
 * @Author hanyaguang
 * @Date 2022/2/18 11:28
 * @Version 1.0
 */
//@Configuration
//@EnableMongoRepositories(basePackages = "com.cdf.mall.mongo.secondary",mongoTemplateRef = "secondaryMongoTemplate")
public class SecondaryMongoConfig {
    @RefreshScope
    @Bean
    @ConfigurationProperties(prefix="spring.data.mongodb.secondary")
    public MongoProperties secondaryMongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(simpleMongoClientDatabaseFactory(secondaryMongoProperties()));
        return mongoTemplate;
    }

    @Bean(name = "secondaryMongoFactory")
    public SimpleMongoClientDatabaseFactory simpleMongoClientDatabaseFactory(MongoProperties settings){
        return new SimpleMongoClientDatabaseFactory(settings.getUri());
    }
}
