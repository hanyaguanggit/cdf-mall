package com.cdf.mall.config;

import com.cdf.mall.service.CdfUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义Web授权配置（授权前置，配置符合自己系统的规则）
 * hyg
 */
@Configuration
@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CdfUserDetailService cdfUserDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cdfUserDetailService);
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                return bCryptPasswordEncoder.encode(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
               // s = bCryptPasswordEncoder.encode(charSequence);
                if (!bCryptPasswordEncoder.matches(charSequence, s)) {
                    log.info("Authentication failed: password error");
                    return false;
                } else {
                    log.info("Authentication ok: ok");
                    return true;
                }

            }
        };
    }
    /**
     * 设置前台静态资源不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/css/**", "/images/**");
    }




    /**
     * description: 安全过滤器链配置，自定义安全访问策略
     * @Param:
     * @Return: 
     * @Author: hanyaguang
     * @Date: 2022/4/28 11:48
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll()
                .and().authorizeRequests()
                .antMatchers("/oauth/**").permitAll()// 批准带/oauth的路由
                .antMatchers("/login.html").permitAll()// 批准所有的路由
                .anyRequest()
                .authenticated()  //任何请求都需要被认证，必须要登录后被访问
                .and().logout().permitAll()
                .and().csrf().disable();//关闭csrf，否则页面无法访问
    }
    
    
}
