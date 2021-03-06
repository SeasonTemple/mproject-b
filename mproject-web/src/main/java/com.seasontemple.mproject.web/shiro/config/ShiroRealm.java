package com.seasontemple.mproject.web.shiro.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.dto.RoleAuth;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.mapper.*;
import com.seasontemple.mproject.dao.redis.JedisUtil;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.exception.CustomException;
import com.seasontemple.mproject.utils.token.TokenUtil;
import com.seasontemple.mproject.utils.token.TokenUtilImpl;
import com.seasontemple.mproject.web.shiro.jwt.JwtToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Season Temple
 * @program: mproject
 * @description: shiro域
 * @create: 2020/03/29 21:38:25
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    private static Log log = LogFactory.get();

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private OperationAuthorityMapper operationAuthorityMapper;

    @Autowired
    private RoleAuthMapper roleAuthMapper;

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

    /*@Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        return super.getAuthorizationInfo(principals);
    }*/

    /**
     * @Description: 只有当需要检测用户权限的时候才会调用此方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) throws AuthorizationException {
        log.warn("进入角色授权......");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String account = tokenUtil.getClaim(principals.toString(), NormalConstant.ACCOUNT);
        log.info("用户名为：{}", account);
        LambdaQueryWrapper<UserRole> userRoleWrapper = Wrappers.lambdaQuery();
        UserRole result = userRoleMapper.selectOne(userRoleWrapper.eq(UserRole::getUserName, account));
        if (!StrUtil.isNotEmpty(result.getRoleName())) {
            throw new AuthorizationException("账户不存在！");
        }
//        List<OperationAuthority> permissionList = new LambdaQueryChainWrapper<>(operationAuthorityMapper)
//                .like(OperationAuthority::getPermission, result.getRoleName())
//                .list();
//        Set<String> permissions = permissionList.stream().map(OperationAuthority::getPermission).collect(Collectors.toSet());
//        Set<String> permissions = permissionList.stream().map(OperationAuthority::getPermission).map(String::toLowerCase).collect(Collectors.toSet());
        List<RoleAuth> roleAuthList = new LambdaQueryChainWrapper<>(roleAuthMapper).select(RoleAuth::getAuth,RoleAuth::getPermission).eq(RoleAuth::getName, result.getRoleName()).list();
        Set<String> permissions = roleAuthList.stream().map(RoleAuth::getPermission).collect(Collectors.toSet());
        Set<String> roles = new HashSet<>();
        roles.add(result.getRoleName());
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    //认证
    //这个token就是从过滤器中传入的jwtToken
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = StrUtil.toString(token.getPrincipal()).trim();
        log.warn("{}", jwt);
        if (StrUtil.isEmpty(jwt)) {
            log.error("jwtToken 不允许为空");
//            throw new NullPointerException("jwtToken 不允许为空");
            throw new AuthenticationException("jwtToken 不允许为空");
        }
//        String password = StrUtil.toString(token.getCredentials());
        //下面是验证这个user是否是真实存在的
        String username = tokenUtil.getClaim(jwt, NormalConstant.ACCOUNT);//判断数据库中username是否存在
        UserRole logUser = Optional.ofNullable(new LambdaQueryChainWrapper<>(userRoleMapper)
                .select(UserRole::getUserName,UserRole::getPassWord, UserRole::getSalt, UserRole::getRoleName, UserRole::getToken)
                .eq(UserRole::getUserName, username)
                .last("limit 1").one()).orElseThrow(() -> new CustomException("该用户不存在"));
/*
        List<OperationAuthority> permissionList = new LambdaQueryChainWrapper<>(operationAuthorityMapper)
                .like(OperationAuthority::getPermission, logUser.getRoleName())
                .list();
        Set<String> permissions = permissionList.stream().map(OperationAuthority::getPermission).collect(Collectors.toSet());
*/

        TokenUtil tokenTool = TokenUtilImpl.build(username);
        // 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
        if (tokenTool.verify(jwt) && JedisUtil.exists(NormalConstant.PREFIX_SHIRO_REFRESH_TOKEN + username)) {
            // 获取RefreshToken的时间戳
            Long currentTimeMillisRedis = Long.valueOf(JedisUtil.getJson(NormalConstant.PREFIX_SHIRO_REFRESH_TOKEN + username));
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            String nowTimeMillis = tokenTool.getClaim(jwt, NormalConstant.CURRENT_TIME_MILLIS);
            if (CompareUtil.compare(nowTimeMillis, StrUtil.toString(currentTimeMillisRedis)) < 0) {
                    return new SimpleAuthenticationInfo(jwt, jwt , new MyByteSource(logUser.getSalt()), getName());
//                return new SimpleAuthenticationInfo(jwt, jwt, "shiroRealm");
            } else {
                throw new AuthenticationException("Token已过期(Token expired or incorrect.)");
            }
        } else {
//            throw new CustomException("jwtToken验证失败");
            throw new CustomException("该用户已退出！");
        }
        /*if (!tokenUtil.verify(jwt)) {
//            throw new CustomException("jwtToken验证失败");
            throw new AuthenticationException("jwtToken验证失败");
        }*/
//        log.info("在使用token登录 {}", username);
//        return new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");
        //这里返回的是类似账号密码的东西，但是jwtToken都是jwt字符串。还需要一个该Realm的类名
    }

}
