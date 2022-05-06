package com.cdf.mall.feign.user;


import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.dto.req.ReqSecurityUserVo;
import com.cdf.mall.dto.resp.CsSecurityUserRespVo;
import com.cdf.mall.entity.CsSecurityUser;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient(value = "cdf-admin",path = "/admin/sso" , fallbackFactory = CsAdminSecurityUserServiceFallbackFactory.class )
public interface CsAdminSecurityUserFeignService {
    @Schema(description = "获取安全认证会员信息")
    @RequestMapping(value = "/getAdminSecurityMember", method = RequestMethod.POST)
    @ResponseBody
    CommonResult<CsSecurityUser> getSecurityAdminUser(@RequestBody ReqSecurityUserVo csMemberReqVo);
}