package com.cdf.mall.feign.user;

import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.dto.req.ReqSecurityUserVo;
import com.cdf.mall.dto.resp.CsSecurityUserRespVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * description:
 * @Param: 后台任务降级服务
 * @Return:
 * @Author: hanyaguang
 * @Date: 2021/10/4 15:03
 */
@Slf4j
@Component
public class CsAdminSecurityUserServiceFallbackFactory implements FallbackFactory<CsAdminSecurityUserFeignService> {
    
    @Override
    public CsAdminSecurityUserFeignService create(Throwable throwable) {

        log.error(this.getClass().getSimpleName() + "feign异常,{}",throwable.getMessage());

        return new CsAdminSecurityUserFeignService() {

            @Override
            public CommonResult<CsSecurityUserRespVo> getSecurityAdminUser(ReqSecurityUserVo csMemberReqVo) {
                return null;
            }
        };
    }
    
    
}
