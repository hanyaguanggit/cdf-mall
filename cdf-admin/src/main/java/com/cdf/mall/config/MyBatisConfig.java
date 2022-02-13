package com.cdf.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 * Created on 2019/4/8.
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.cdf.mall.db1.mapper","com.cdf.mall.db2.mapper"})
public class MyBatisConfig {

}
