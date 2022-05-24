package com.cdf.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


/**
 *hyg
 */
@Configuration
public class RibbonConfig {
    
    @Autowired
    private LoadBalancerClient loadBalancer;
    @Bean
    //@LoadBalanced    SmartInitializingSingleton   InitializingBean （构建bean的init方法）
    // 顺序的问题 SmartInitializingSingleton是在所有的非懒加载单例bean构建完成之后调用的
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(
                Collections.singletonList(
                        new LoadBalancerInterceptor(loadBalancer)));
    
        return restTemplate;
    }

//    /**
//     * 给RestTemplate提前注入LoadBalancerInterceptor
//     *
//     *YGL
//     */
//    @Bean
//    public RestTemplate CDFRestTemplate(LoadBalancerClient loadBalancer){
//        RestTemplate restTemplate = new RestTemplate();
//        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//        // ribbon核心拦截器
//        interceptors.add(new LoadBalancerInterceptor(loadBalancer));
//        restTemplate.setInterceptors(interceptors);
//        return restTemplate;
//    }
//
}
