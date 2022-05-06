package com.cdf.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hyg
 * @Date: 2021/9/7 14:20
 * @Descrption: http连接池配置类
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "http-pool")
public class HttpPoolProperties {
    private Integer maxTotal;
    private Integer defaultMaxPerRoute;
    private Integer connectTimeout;
    private Integer connectionRequestTimeout;
    private Integer socketTimeout;
    private Integer validateAfterInactivity;
}
