package com.cdf.mall.config;

import com.mongodb.ClientSessionOptions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @Description 主库
 * @Author hanyaguang
 * @Date 2022/2/17 10:49
 * @Version 1.0
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.cdf.mall.mongo.primary",mongoTemplateRef = "primaryMongoMongoTemplate")
public class PrimaryMongoMongoTemplate {


}
