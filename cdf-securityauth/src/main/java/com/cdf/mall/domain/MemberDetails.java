package com.cdf.mall.domain;

import com.cdf.mall.dto.resp.CsSecurityUserRespVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 *hyg
 */
public class MemberDetails implements UserDetails {
    private CsSecurityUserRespVo csSecurityUserRespVo;
    
    public MemberDetails(CsSecurityUserRespVo CsMember) {
        this.csSecurityUserRespVo = CsMember;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限  BRAC   user  role  authority
        //TODO??? 此处后期可以进行扩展
        return Arrays.asList(new SimpleGrantedAuthority("TEST"));
    }
    
    @Override
    public String getPassword() {
        String pwd = csSecurityUserRespVo.getPassword();
        return pwd;
    }
    
    @Override
    public String getUsername() {

        return csSecurityUserRespVo.getUsername();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return csSecurityUserRespVo.getEnabled()==true;
    }
    
    public CsSecurityUserRespVo getCsMember() {
        return csSecurityUserRespVo;
    }
}