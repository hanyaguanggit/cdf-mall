package com.cdf.mall.config;

import com.cdf.mall.common.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description redis多数据源配置类
 * @Author hanyaguang
 * @Date 2022/2/14 16:51
 * @Version 1.0
 */
@Configuration
public class RedisTemplateConfig {

    //order
    @Value("${spring.redis.order.host}")
    private String orderHost;

    @Value("${spring.redis.order.port}")
    private String orderPort;

    @Value("${spring.redis.order.password}")
    private String orderPassword;

    //user
    @Value("${spring.redis.user.host}")
    private String userHost;

    @Value("${spring.redis.user.port}")
    private String userPort;

    @Value("${spring.redis.user.password}")
    private String userPassword;

    private static final int MAX_IDLE = 200; //最大空闲连接数
    private static final int MAX_TOTAL = 1024; //最大连接数
    private static final long MAX_WAIT_MILLIS = 10000; //建立连接最长等待时间

    //连接池配置
    public JedisPoolConfig poolConfig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000L);
        return poolConfig;
    }

    //配置工厂
    public RedisConnectionFactory connectionFactory(String host, int port, String password, int maxIdle,
                                                    int maxTotal, long maxWaitMillis, int index) {

        JedisPoolConfig poolConfig = poolConfig(maxIdle, maxTotal, maxWaitMillis, true);

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setDatabase(index);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        redisStandaloneConfiguration.setPort(port);

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder = JedisClientConfiguration.builder();
        JedisClientConfiguration jedisClientConfiguration = jedisClientConfigurationBuilder.usePooling().poolConfig(poolConfig).build();
        return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
    }


    @Bean(name = "redisOrderTemplate")
    public StringRedisTemplate redisOrderTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(
                connectionFactory(orderHost, Integer.parseInt(orderPort), orderPassword, MAX_IDLE, MAX_TOTAL, MAX_WAIT_MILLIS, 0));
        return template;
    }

    //------------------------------------
    @Bean(name = "redisUserTemplate")
    public StringRedisTemplate userUserTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(
                connectionFactory(userHost, Integer.parseInt(userPort), userPassword, MAX_IDLE, MAX_TOTAL, MAX_WAIT_MILLIS, 0));
        return template;
    }
}
