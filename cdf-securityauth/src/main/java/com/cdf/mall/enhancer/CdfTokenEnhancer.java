package com.cdf.mall.enhancer;


import com.cdf.mall.domain.MemberDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import java.util.HashMap;
import java.util.Map;

/**
 * hyg
 * token增强器
 */
@Slf4j
public class CdfTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();

        final Map<String, Object> additionalInfo = new HashMap<>();

        final Map<String, Object> retMap = new HashMap<>();

        //todo 这里暴露memberId到Jwt的令牌中,后期可以根据自己的业务需要 进行添加字段
        additionalInfo.put("memberId",memberDetails.getCsMember().getId());
        additionalInfo.put("mobilePhoneNo",memberDetails.getCsMember().getMobilephoneno());
        additionalInfo.put("loginName",memberDetails.getCsMember().getLoginname());

        retMap.put("additionalInfo",additionalInfo);
        log.info(" accessToken retMap........................ "+retMap);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(retMap);

        return accessToken;
    }
}
