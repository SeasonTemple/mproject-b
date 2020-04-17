package com.seasontemple.mproject.web.shiro.jwt;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;

import java.util.Collection;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 授权信息
 * @create: 2020/04/17 10:43:25
 */
public class AuthToken implements AuthorizationInfo {
    @Override
    public Collection<String> getRoles() {
        return null;
    }

    @Override
    public Collection<String> getStringPermissions() {
        return null;
    }

    @Override
    public Collection<Permission> getObjectPermissions() {
        return null;
    }
}
