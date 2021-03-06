package com.seasontemple.mproject.web.shiro.jwt;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/**
 * @author Season Temple
 * @program: mproject
 * @description: JwtToken实体类
 * @create: 2020/03/29 21:33:57
 */
public class JwtToken implements AuthenticationToken {

    private static final Log log = LogFactory.get();
    private String jwt;

    public JwtToken(String jwt) {
        this.jwt = jwt;
    }

    @Override//类似是用户名
    public Object getPrincipal() {
        return jwt;
    }

    @Override//类似密码
    public Object getCredentials() {
        return jwt;
    }

}
