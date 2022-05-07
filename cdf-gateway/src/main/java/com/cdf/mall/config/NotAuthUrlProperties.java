package com.cdf.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;

/**
 * ygl
 */
@Data
@ConfigurationProperties("cdf.gateway")
public class NotAuthUrlProperties {
    private LinkedHashSet<String> shouldSkipUrls;
}