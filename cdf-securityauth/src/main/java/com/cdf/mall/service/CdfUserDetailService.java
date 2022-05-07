package com.cdf.mall.service;
import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.domain.MemberDetails;
import com.cdf.mall.dto.req.ReqSecurityUserVo;
import com.cdf.mall.dto.resp.CsSecurityUserRespVo;
import com.cdf.mall.entity.CsSecurityUser;
import com.cdf.mall.feign.user.CsAdminSecurityUserFeignService;
import com.cdf.mall.util.security.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 *hyg
 */
@Component
@Slf4j
public class CdfUserDetailService implements UserDetailsService {

    @Autowired
    private CsAdminSecurityUserFeignService csAdminSecurityUserFeignService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDetails memberDetails = null;
        // TODO  查数据库获取用户信息   rpc调用
        // 加载用户信息
        if (!StringUtils.isNotEmpty(username)) {
            log.warn("用户登陆用户名为空:{}", username);
            throw new UsernameNotFoundException("用户名不能为空");
        }

        CsSecurityUser securityUser = getByUsername(username);
        
        if (null == securityUser) {
            log.warn("根据用户名没有查询到对应的用户信息:{}", username);
        }
        String password= null;
        try {
            password = AesUtils.decrypt("",securityUser.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(password != null){
            String passwordnew = passwordEncoder.encode(password);
            securityUser.setPassword(passwordnew);
            //CsMember.setUsername(CsMember.getUsername());
            log.info("根据用户名:{}获取用户登陆信息:{}", username, securityUser);

            // 会员信息的封装 implements UserDetails
            memberDetails = new MemberDetails(securityUser);
        }
        return memberDetails;
    }

    
    public CsSecurityUser getByUsername(String loginName) {
        ReqSecurityUserVo reqSecurityUserVo= new ReqSecurityUserVo();
        reqSecurityUserVo.setLoginName(loginName);
        // fegin获取会员信息
        //远程调用会员查询接口   /oauth/token
        long startTime = System.currentTimeMillis();
        CommonResult<CsSecurityUser> csSecurityUserRespVo =csAdminSecurityUserFeignService.getSecurityAdminUser(reqSecurityUserVo);
        long endTime = System.currentTimeMillis();
        log.info("远程调用会员查询接口 {}",(int) (endTime - startTime));
        
        return csSecurityUserRespVo.getData();
    }
}
