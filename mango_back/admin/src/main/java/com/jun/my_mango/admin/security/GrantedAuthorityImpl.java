package com.jun.my_mango.admin.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Description: 权限封装
 * @author: Liusu
 * @date: 2022年11月11日14:01
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority){
        this.authority = authority;
    }

    public void setAuthority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
