package com.cdf.mall.domain;

import com.cdf.mall.entity.CsSecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;
import java.util.Collection;

/**
 *hyg
 */
public class MemberDetails implements UserDetails {
    private CsSecurityUser csSecurityUser;
    
    public MemberDetails(CsSecurityUser CsMember) {
        this.csSecurityUser = CsMember;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限  BRAC   user  role  authority
        //TODO??? 此处后期可以进行扩展
        return Arrays.asList(new SimpleGrantedAuthority("TEST"));
    }
    
    @Override
    public String getPassword() {
        String pwd = csSecurityUser.getPassword();
        return pwd;
    }
    
    @Override
    public String getUsername() {

        return csSecurityUser.getUsername();
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
        return csSecurityUser.getEnabled()==true;
    }
    
    public CsSecurityUser getCsMember() {
        return csSecurityUser;
    }
}