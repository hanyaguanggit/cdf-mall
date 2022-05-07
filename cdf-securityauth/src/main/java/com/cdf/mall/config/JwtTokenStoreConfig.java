package com.cdf.mall.config;

import com.alibaba.fastjson.JSON;
import com.cdf.mall.enhancer.CdfTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * hyg
 * jwt授权令牌token的存储配置
 */

@Configuration
@EnableConfigurationProperties(value = JwtCAProperties.class)
public class JwtTokenStoreConfig {
    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private JwtCAProperties jwtCAProperties;

    //使用内存对象jwtTokenStore保存jwtToken
    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    //使用redis保存token
    //@Bean
//    public TokenStore redisTokenStore(){
//
//        RedisTokenStore redis = new RedisTokenStore(connectionFactory);
//        return redis;
//    }

    @Bean
    public CdfTokenEnhancer cdfTokenEnhancer() {
        return new CdfTokenEnhancer();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new
                JwtAccessTokenConverter();
        //配置JWT使用的秘钥(相当于加密中的加盐)
        //accessTokenConverter.setSigningKey("KJHUhjjJYgYUllVbXhKDHXhkSyHjlNiVkYzWTBac1Yxkjhuad");
        accessTokenConverter.setSigningKey("123456");
        //配置JWT使用的秘钥 非对称加密（私钥和公钥加密方式）
         accessTokenConverter.setKeyPair(keyPair());
        return accessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(jwtCAProperties.getKeyPairName()), jwtCAProperties.getKeyPairSecret().toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(jwtCAProperties.getKeyPairAlias(), jwtCAProperties.getKeyPairStoreSecret().toCharArray());
        System.out.println("密钥对=="+ JSON.toJSONString(keyPair));
        System.out.println("公钥=="+JSON.toJSONString(keyPair.getPublic()));
        return keyPair;
    }
}