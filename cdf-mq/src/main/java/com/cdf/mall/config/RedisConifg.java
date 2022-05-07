package com.cdf.mall.config;

import com.cdf.mall.util.RedisOpsUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
/*import org.redisson.Redisson;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;*/
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 *hyg
 **/
@Slf4j
@Configuration
public class RedisConifg {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int  min_idle;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int max_idle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int max_wait;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int max_active;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(max_active);
        config.setMaxIdle(max_wait);
        config.setMaxWaitMillis(max_wait);
        config.setMinIdle(min_idle);
        //其他属性可以自行添加
        return config;
    }



        @Bean
        public JedisConnectionFactory jedisConnectionFactory() {

            // config.setTestOnBorrow(TEST_ON_BORROW);  

//           RedisStandaloneConfiguration rconfig = new RedisStandaloneConfiguration();
//            rconfig.setHostName(host);
//            rconfig.setPort(port);
//            rconfig.setPassword(password);

            JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig());
            connectionFactory.setHostName(host);
            connectionFactory.setPort(port);
            connectionFactory.setPassword(password);
            return connectionFactory;
        }

        @Bean
        @Primary
        public RedisTemplate<String, Object> redisTemplate() {
            RedisTemplate<String, Object> template = new RedisTemplate();
            template.setConnectionFactory(jedisConnectionFactory());
            //GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
            // 序列化后会产生java类型说明，如果不需要用“Jackson2JsonRedisSerializer”和“ObjectMapper ”配合效果更好
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jackson2JsonRedisSerializer.setObjectMapper(om);

            StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
            template.setKeySerializer(stringRedisSerializer);
            template.setValueSerializer(jackson2JsonRedisSerializer);

            template.setHashKeySerializer(jackson2JsonRedisSerializer);
            template.setHashValueSerializer(jackson2JsonRedisSerializer);

            template.afterPropertiesSet();
            return template;
        }


        @Bean
        public RedisOpsUtil redisOpsUtil() {
            return new RedisOpsUtil();
        }

        /**
         * hyg
         * 分布式锁
         * @return redisson
         *//*
        @Bean(destroyMethod="shutdown")
        public Redisson redisson(){
            StringBuilder url = new StringBuilder();
            url.append("redis://").append(host).append(":").append(port);
            Config config = new Config();
            ClusterServersConfig clusterServersConfig = config.useClusterServers()
                    .addNodeAddress(url.toString());
            clusterServersConfig.setPassword(password);//设置密码
            return (Redisson) Redisson.create(config);
        }*/

}
