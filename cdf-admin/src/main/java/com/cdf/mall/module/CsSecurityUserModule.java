package com.cdf.mall.module;

import com.cdf.mall.common.TokenInfo;
import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.commons.ResultAdminCode;
import com.cdf.mall.dto.req.ReqSecurityUserVo;
import com.cdf.mall.model.master.CsSecurityUser;
import com.cdf.mall.model.master.CsSecurityUserExample;
import com.cdf.mall.service.master.CsSecurityUserService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * hyg 2021.07.16
 * 后台管理员相关
 */
@Component
public class CsSecurityUserModule {
    Logger logger = LoggerFactory.getLogger(CsSecurityUserModule.class);

    @Autowired
    private CsSecurityUserService csSecurityUserService;



    /**
     * ygl
     * 登录后获取token
     */
    public TokenInfo login(String username, String password){
        return csSecurityUserService.login(username,password);
    }
    /**
     * ygl
     * 刷新token
     */
    public String refreshToken(String token){
        return csSecurityUserService.refreshToken(token);

    }

    /**
     * 根据用户名查询用户
     * @param request
     * @return
     */
    public CommonResult<CsSecurityUser> loadUserByUsername(ReqSecurityUserVo request){
        CsSecurityUserExample csSecurityUserExample = new CsSecurityUserExample();
        CommonResult<CsSecurityUser> response = new CommonResult<>();
        CsSecurityUser csSecurityUser=null;

        try {
           // BeanUtils.copyProperties(request, csSecurityUserExample);
            csSecurityUserExample.createCriteria().andLoginnameEqualTo(request.getLoginName());
            csSecurityUser = csSecurityUserService.loadUserByUsername(csSecurityUserExample);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询后端认证会员信息异常 ",e.getMessage());
            response.setCode(ResultAdminCode.FAILED.getCode());
            response.setMessage(ResultAdminCode.FAILED.getMessage());
            return response;
        }
        if(ObjectUtils.isEmpty(csSecurityUser)){
            logger.info("查询后端认证会员信息空值",csSecurityUser);
            response.setCode(ResultAdminCode.FAILED.getCode());
            response.setMessage(ResultAdminCode.FAILED.getMessage());
            return response;
        }
        response.setData(csSecurityUser);
        response.setMessage(ResultAdminCode.SUCCESS.getMessage());
        response.setCode(ResultAdminCode.SUCCESS.getCode());
        System.out.println("根据用户名称加载用户===>"+response);
        return response;
    }

}
