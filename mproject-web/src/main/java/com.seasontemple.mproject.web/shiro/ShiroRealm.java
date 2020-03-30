package com.seasontemple.mproject.web.shiro;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import com.seasontemple.mproject.utils.tool.TokenUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Season Temple
 * @program: mproject
 * @description: shiro域
 * @create: 2020/03/29 21:38:25
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private TokenUtil tokenUtil;

    /*
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     * */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    //这个token就是从过滤器中传入的jwtToken
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String jwt = (String) token.getPrincipal();
        if (jwt == null) {
            throw new NullPointerException("jwtToken 不允许为空");
        }
        //判断
        if (!tokenUtil.verify(jwt)) {
            throw new UnknownAccountException();
        }
        //下面是验证这个user是否是真实存在的
        String username = (String) tokenUtil.parse(jwt).get("username");//判断数据库中username是否存在
        StaticLog.info("在使用token登录 {}", username);
        return new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");
        //这里返回的是类似账号密码的东西，但是jwtToken都是jwt字符串。还需要一个该Realm的类名
    }

}
