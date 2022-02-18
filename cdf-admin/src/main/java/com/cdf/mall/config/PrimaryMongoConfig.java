package com.cdf.mall.config;

import com.mongodb.ClientSessionOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ConnectionPoolSettings;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description mongodb 主数据库配置类
 * @Author hanyaguang
 * @Date 2022/2/17 9:11
 * @Version 1.0
 */
@Configuration
//@EnableMongoRepositories(basePackages = "com.cdf.mall.mongo.primary",mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {
    @RefreshScope
    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.data.mongodb.primary")
    public MongoProperties primaryMongoProperties() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(simpleMongoClientDatabaseFactory(primaryMongoProperties()));
        return mongoTemplate;
    }

    @Primary
    @Bean
    public SimpleMongoClientDatabaseFactory simpleMongoClientDatabaseFactory(MongoProperties properties) {
        MongoClientSettings settings;
        List<ServerAddress> serverAddressList = new ArrayList<>();
        System.out.println("properties:"+properties.toString());

        String host = properties.getUri().substring(0, properties.getUri().lastIndexOf(":"));
        String port = properties.getUri().substring(properties.getUri().lastIndexOf(":") + 1);
        int portint = Integer.parseInt(port);
       // System.out.println("port="+portint);
        serverAddressList.add(new ServerAddress(host, portint));

        ConnectionPoolSettings poolSetting = ConnectionPoolSettings.builder().
                maxWaitTime(30000, TimeUnit.MILLISECONDS).build();

        // 连接认证
        // 创建客户端和Factory
        MongoClient mongoClient = null;
        if (StringUtils.isNotEmpty(properties.getUsername())) {
            MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(
                    properties.getUsername(),
                    properties.getAuthenticationDatabase() != null ? properties
                            .getAuthenticationDatabase() : properties.getDatabase(),
                    properties.getPassword());
            settings = MongoClientSettings.builder()
                    .credential(mongoCredential)
                    .applyToConnectionPoolSettings(builder -> builder.applySettings(poolSetting))
                    .applyToClusterSettings(builder -> builder.hosts(serverAddressList)).build();
        } else {
            settings = MongoClientSettings.builder().applyToConnectionPoolSettings(builder -> builder.applySettings(poolSetting))
                    .applyToClusterSettings(builder -> builder.hosts(serverAddressList)).build();
        }
        mongoClient = MongoClients.create(settings);
        // 创建MongoDbFactory
        SimpleMongoClientDatabaseFactory mongoFactory = new SimpleMongoClientDatabaseFactory(mongoClient, properties.getDatabase());
        return mongoFactory;
    }
}
