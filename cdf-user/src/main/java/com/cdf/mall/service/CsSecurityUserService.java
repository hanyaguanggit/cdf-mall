package com.cdf.mall.service;
import com.cdf.mall.common.TokenInfo;

public interface CsSecurityUserService {


   // public CsSecurityUser loadUserByUsername(CsSecurityUserExample csSecurityUserExample);

    /**
     * ygl
     * 登录成功获取token
     * 登录后获取token
     */
    TokenInfo login(String username, String password);

    /**
     * ygl
     * 刷新token
     * @param token
     * @return
     */
    String refreshToken(String token);

}
