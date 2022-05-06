package com.cdf.mall.service.master;

import com.cdf.mall.common.TokenInfo;
import com.cdf.mall.constant.UserAuth;
import com.cdf.mall.mapper.master.CsSecurityUserMapper;
import com.cdf.mall.model.master.CsSecurityUser;
import com.cdf.mall.model.master.CsSecurityUserExample;
import com.cdf.mall.util.RedisOrderOpsUtil;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Slf4j
public class CsSecurityUserServiceImpl implements CsSecurityUserService {


    @Autowired
    private RestTemplate restTemplate;

   // @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

   // @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RedisOrderOpsUtil redisOpsUtil;

    @Autowired
    private CsSecurityUserMapper csSecurityUserMapper;


    @Override
    public CsSecurityUser loadUserByUsername(CsSecurityUserExample csSecurityUserExample) {
        List<CsSecurityUser> csSecurityUserList = csSecurityUserMapper.selectByExample(csSecurityUserExample);
        if(!CollectionUtils.isEmpty(csSecurityUserList)){
            return csSecurityUserList.get(0);
        }else{
            return null;
        }
    }

    @Override
    /**
     * ygl
     * 单点登录通过用户名密码验证登录
     */
    public TokenInfo login(String username, String password) {
        ResponseEntity<TokenInfo> response;
        try {
            //远程调用认证服务器 进行用户登陆   /oauth/token
            // long startTime = System.currentTimeMillis();
            log.info("远程调用认证服务器 进行用户登陆 {}", username + "-" + password);
            response = restTemplate.exchange(UserAuth.OAUTH_LOGIN_URL, HttpMethod.POST, wrapOauthTokenRequest(username, password), TokenInfo.class);
            // long endTime = System.currentTimeMillis();
            if (response == null) {
                log.error("根据用户名  response 信息:{}" + response);
                return null;
            }
            TokenInfo tokenInfo = response.getBody();
            return tokenInfo;
        } catch (Exception e) {
            log.error("根据用户名:{}登陆异常:{}", username, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法实现说明:封装用户到认证中心的请求头 和请求参数
     * @author:ygl
     * @param userName 用户名
     * @param password 密码
     * @return:
     * @exception:
     * @date:2020/1/22 15:32
     */
    private HttpEntity<MultiValueMap<String, String>> wrapOauthTokenRequest(String userName, String password) {

        //封装oauth2 请求头 clientId clientSecret
        HttpHeaders httpHeaders = wrapHttpHeaders();

        //封装请求参数
        MultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
        reqParams.add(UserAuth.USER_NAME,userName);
        reqParams.add(UserAuth.PASS,password);
        reqParams.add(UserAuth.GRANT_TYPE,UserAuth.PASS);
        reqParams.add(UserAuth.SCOPE,UserAuth.SCOPE_AUTH);

        //封装请求参数
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(reqParams, httpHeaders);
        return entity;
    }

    /**
     * 方法实现说明:封装刷新token的请求
     * @author:ygl
     * @param refreshToken:刷新token
     * @return: HttpEntity
     * @exception:
     * @date:2020/1/22 16:14
     */
    private HttpEntity<MultiValueMap<String, String>> wrapRefreshTokenRequest(String refreshToken) {

        HttpHeaders httpHeaders = wrapHttpHeaders();

        MultiValueMap<String,String> param = new LinkedMultiValueMap<>();
        param.add("grant_type","refresh_token");
        param.add("refresh_token",refreshToken);

        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(param,httpHeaders);
        return httpEntity;
    }

    /**
     * 方法实现说明:封装请求头
     * @author:ygl
     * @return:HttpHeaders
     * @exception:
     * @date:2020/1/22 16:10
     */
    private HttpHeaders wrapHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(UserAuth.CLIENT_ID,UserAuth.CLIENT_SECRET);
        return httpHeaders;
    }

    @Override
    /**
     * 刷新token
     */
    public String refreshToken(String token) {

        log.info("RefreshToken的值为:{}",token);

        if(StringUtil.isEmpty(token)) {
            log.warn("刷新令牌不能为空:{}",token);
            return null;
        }
        ResponseEntity<TokenInfo> responseEntity = null ;

        String jwtTokenValue=null ;

        try{
            jwtTokenValue = token.substring(tokenHead.length()).trim();
            //刷新令牌
            responseEntity = restTemplate.exchange(UserAuth.OAUTH_LOGIN_URL, HttpMethod.POST, wrapRefreshTokenRequest(jwtTokenValue), TokenInfo.class);

            TokenInfo tokenInfo = responseEntity.getBody();

            String newAccessToken = tokenInfo.getAccess_token();

            log.info("通过RefreshToken:{}刷新令牌成功accessToken:{}",jwtTokenValue,newAccessToken);

            return newAccessToken;

        }catch (Exception e) {
            e.printStackTrace();
            log.error("通过RefreshToken:{}刷新令牌失败:{}",jwtTokenValue,e.getMessage());
            return null;
        }
    }


    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone){
        if(StringUtils.isEmpty(authCode)){
            return false;
        }
        String realAuthCode = redisOpsUtil.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }


}
