package com.cdf.mall.controller;

import com.alibaba.fastjson.JSON;
import com.cdf.mall.common.TokenInfo;
import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.dto.req.ReqSecurityUserVo;
import com.cdf.mall.model.master.CsSecurityUser;
import com.cdf.mall.module.CsSecurityUserModule;
import com.cdf.mall.util.RedisOrderOpsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 登录注册获取验证码等
 * @Param:
 * @Return:
 * @Author: yaoguanglei
 * @Date: 2021/9/19 18:49
 */
@RestController
//@Api(tags = "CsMemberSSOController")
@RequestMapping("/admin/sso")
@Slf4j
public class CsSecurityUserSSOController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private CsSecurityUserModule csSecurityUserModule;
    @Autowired
    private RedisOrderOpsUtil redisOpsUtil;

   // @ApiOperation("后台会员单点登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonResult login(@Valid @RequestBody ReqSecurityUserVo request, Errors errors) {
        //登录前进行校验
       /* CommonResult<Boolean> validUser = csSecurityUserModule.validSecurityUser(request);
        if(validUser.getData() == false){
            return validUser;
        }*/
        TokenInfo tokenInfo = csSecurityUserModule.login(request.getLoginName(), request.getPassword());
        if (tokenInfo == null) {
            log.info("用户名或密码错误 tokenInfo"+tokenInfo);
            return CommonResult.validateFailed("用户名或密码错误");
        }
        //更新登录时间
       /* String memberId = tokenInfo.getAdditionalInfo().get("memberId");
        csSecurityUserModule.updateLastLoginTime(memberId);*/

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", tokenInfo.getAccess_token());
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("refreshToken",tokenInfo.getRefresh_token());
        tokenMap.put("memberId",tokenInfo.getAdditionalInfo().get("memberId"));
        tokenMap.put("loginName",request.getLoginName());
        return CommonResult.success(tokenMap);
    }
    /**
     * ygl
     * 后台用户securityuser信息
     * @param request
     * @return
     */
   // @ApiOperation("查询安全用户认证模块会员信息")
    @PostMapping(value = "/getAdminSecurityMember", consumes = "application/json")
    public CommonResult<CsSecurityUser> getSecurityMember(@Valid @RequestBody ReqSecurityUserVo request, Errors errors) {
        CommonResult<CsSecurityUser> response = new CommonResult<>();

        try {
             response = csSecurityUserModule.loadUserByUsername(request);
        } catch (Exception e ) {
            e.printStackTrace();
            return CommonResult.failed(response.getCode(),e.getMessage());
        }
        System.out.println("控制层中的用户信息---->"+ JSON.toJSONString(response));
        return response;
    }

   // @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshAdminToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshAdminToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = csSecurityUserModule.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }




}
