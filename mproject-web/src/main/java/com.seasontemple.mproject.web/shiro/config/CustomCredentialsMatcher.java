package com.seasontemple.mproject.web.shiro.config;

import cn.hutool.core.util.StrUtil;
import com.seasontemple.mproject.utils.token.TokenUtilImpl;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 自定义shiro密码比较器
 * @create: 2020/04/16 15:27:39
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenCredentials = TokenUtilImpl.build(null).getClaim(StrUtil.toString(getCredentials(token)), "passWord");
        Object accountCredentials = getCredentials(info);
        return super.equals(tokenCredentials, accountCredentials);
    }
}
