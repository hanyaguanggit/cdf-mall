/*
package com.cdf.mall.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

*/
/**
 * @author: hyg
 * @Date: 2021/9/7 14:26
 *//*

@Configuration
public class RestTemplateConfig {

    */
/**
     * 配置httpPool信息
     *//*

    @Autowired
    private HttpPoolProperties httpPoolProperties;

    */
/**
     * 配置RestTemplate
     * @return
     *//*

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        HttpClient httpClient = httpClient();
        ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
        // 可以添加消息转换
        //restTemplate.setMessageConverters(...);
        // 可以增加拦截器
        //restTemplate.setInterceptors(...);
        return restTemplate;
    }

    */
/**
     * 配置httpClient
     * @return
     *//*

    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
// 设置整个连接池最大连接数 根据自己的场景决定
        connectionManager.setMaxTotal(httpPoolProperties.getMaxTotal());

// 设置每个路由(域名)最大的线程数
        connectionManager.setDefaultMaxPerRoute(httpPoolProperties.getDefaultMaxPerRoute());

// 设置路由(域名)对应的最大线程数，如果设置则比setDefaultMaxPerRoute(xxx)的优先级高
//connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("v2.moon.com", 80)), 20);

// validateAfterInactivity 空闲永久连接检查间隔，这个牵扯的还比较多,
// 官方推荐使用这个来检查永久链接的可用性，而不推荐每次请求的时候才去检查
        connectionManager.setValidateAfterInactivity(httpPoolProperties.getValidateAfterInactivity());

        RequestConfig requestConfig = RequestConfig.custom()
                // 连接上服务器(握手成功)的时间(可以设置2000)，超出抛出connect timeout
                .setConnectTimeout(httpPoolProperties.getConnectTimeout())
                // 服务器返回数据(response)的时间(可以设置3000)，超过抛出read timeout
                .setSocketTimeout(httpPoolProperties.getSocketTimeout())
                // 从连接池中获取连接的超时时间，超时间未拿到可用连接，会抛出以下异常
                //org.apache.http.conn.ConnectionPoolTimeoutException:
                //Timeout waiting for connection from pool
                .setConnectionRequestTimeout(httpPoolProperties.getConnectionRequestTimeout())
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }
}
*/
