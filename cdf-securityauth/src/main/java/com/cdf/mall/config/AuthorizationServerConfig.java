package com.cdf.mall.config;

import com.cdf.mall.enhancer.CdfTokenEnhancer;
import com.cdf.mall.service.CdfUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ygl
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;
//    @Autowired
//    @Qualifier("redisTokenStore")
  //  private TokenStore redustokenStore;
    
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;//token转换器
    
    @Autowired
    private CdfUserDetailService cdfUserDetailService;
    
    @Autowired
    private AuthenticationManager authenticationManagerBean;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 第三方信息的存储   基于jdbc
        clients.withClientDetails(clientDetailsService());

    }
    @Autowired
    private CdfTokenEnhancer cdfTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       // 配置JWT的内容增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(cdfTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(delegates);

        //使用密码模式需要配置
        endpoints.authenticationManager(authenticationManagerBean)

                .reuseRefreshTokens(false)  //refresh_token是否重复使用
                .userDetailsService(cdfUserDetailService) //刷新令牌授权包含对用户信息的检查
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenStore(tokenStore)//指定token存储策略是jwt
                .tokenEnhancer(enhancerChain) //配置tokenEnhancer
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST); //支持GET,POST请求

    }
    
    /**
     * 授权服务器安全配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //第三方客户端校验token需要带入 clientId 和clientSecret来校验
        security.checkTokenAccess("isAuthenticated()")
                .tokenKeyAccess("isAuthenticated()");//来获取我们的tokenKey需要带入clientId,clientSecret
        
        //允许表单认证
        security.allowFormAuthenticationForClients();
    }
    
    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }
    
}
