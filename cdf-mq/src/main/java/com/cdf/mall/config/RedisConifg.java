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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.HashSet;

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
    private int  min_idle;//连接池中的最小空闲连接
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int max_idle;//连接池中的最大空闲连接
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int max_wait;//连接池最大阻塞等待时间
    @Value("${spring.redis.jedis.pool.max-active}")
    private int max_active;//最大连接数

    @Value("${spring.redis.cluster.nodes}")
    private String node;

    @Value("${spring.redis.cluster.timeout}")
    private int timeout;

   /* @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(max_active);
        config.setMaxIdle(max_idle);
        config.setMaxWaitMillis(max_wait);
        config.setMinIdle(min_idle);
        //其他属性可以自行添加
        return config;
    }*/


   @Bean
   public RedisStandaloneConfiguration standaloneConfiguration(){
       RedisStandaloneConfiguration redisStandalone = new RedisStandaloneConfiguration();
       redisStandalone.setHostName(host);
       redisStandalone.setPort(port);
       redisStandalone.setPassword(password);
       return redisStandalone;
   }

      /*  @Bean
        public JedisConnectionFactory jedisConnectionFactory() {

            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            HashSet<RedisNode> nodes = new HashSet<>();
           // String[] serverArray = redisProperties.getClusterNodes().split(",");
            String[] serverArray = node.split(",");
            for(String server:serverArray){
                String[] ipPortPair = server.split(":");
                RedisNode redisNode = new RedisNode(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim()));
                nodes.add(redisNode);
            }
            redisClusterConfiguration.setClusterNodes(nodes);
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig());
            return jedisConnectionFactory;
        }*/

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(standaloneConfiguration());
        return jedisConnectionFactory;
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
