package com.seasontemple.mproject.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.log.Log;
import cn.hutool.log.StaticLog;
import com.seasontemple.mproject.dao.dto.LoginDto;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.dao.redis.JedisUtil;
import com.seasontemple.mproject.service.service.LoginService;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.custom.ResultCode;
import com.seasontemple.mproject.utils.exception.CustomException;
import com.seasontemple.mproject.utils.token.TokenUtil;
import com.seasontemple.mproject.utils.token.TokenUtilImpl;
import com.seasontemple.mproject.web.shiro.jwt.JwtToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description: Shiro test controller
 * @create: 2020/03/30 21:34:18
 */
@Api(value = "登录接口", tags = "登录接口控制层")
@RestController
public class LoginController extends BaseController {

    private static final Log log = Log.get();

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

//    @Autowired
//    private TokenUtil tokenUtil;
//    @PostMapping("/login")
    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户登录", notes = "使用用户名、密码进行登录")
    @ResponseBody
    public ResponseBean login(LoginDto userDto) throws CustomException{
        log.info("LoginDto：{}", userDto);
        if (BeanUtil.isEmpty(userDto) ) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ResponseBean.builder().msg("登录失败！该账户不存在。").build().success();
        }
        UserRole logUser = loginService.checkLogin(userDto.getUserName());
        if (StrUtil.isEmpty(logUser.getPassWord()) ) {
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseBean.builder().msg("登录失败！该账户不存在。").build().failed();
        }
        AES aes = SecureUtil.aes(logUser.getSalt());
        String deCryptPwd = aes.decryptStr(logUser.getPassWord(), CharsetUtil.CHARSET_UTF_8);
        log.warn("{}", logUser);
        if (!userDto.getPassWord().equals(deCryptPwd)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseBean.builder().msg("登录失败！该账户不存在。").build().failed();
        }
        // 清除可能存在的shiro权限信息缓存
        if (JedisUtil.exists(NormalConstant.PREFIX_SHIRO_CACHE + logUser.getUserName())) {
            JedisUtil.delKey(NormalConstant.PREFIX_SHIRO_CACHE + logUser.getUserName());
        }
        // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
        Long currentTimeMillis = System.currentTimeMillis();
        JedisUtil.setJson(NormalConstant.PREFIX_SHIRO_REFRESH_TOKEN + logUser.getUserName(), StrUtil.toString(currentTimeMillis),
                Integer.parseInt(refreshTokenExpireTime));
        TokenUtil tokenUtil = TokenUtilImpl.build(logUser.getUserName());
        Map<String, Object> claim = new HashMap<>();
        claim.put("userName", logUser.getUserName());
        claim.put("passWord", logUser.getPassWord());
        claim.put("roleId", logUser.getRoleId());
        claim.put("iat", currentTimeMillis);
        String jwtToken = tokenUtil.generate(claim, NormalConstant.ttlMillis);
        claim.put("token", jwtToken);
        try {
            JwtToken logToken = new JwtToken(jwtToken);
            getSubject().login(logToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Authorization", jwtToken);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setStatus(HttpServletResponse.SC_OK);
        return ResponseBean.builder().msg("登录成功！").data(claim).build().success();
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "使用用户名、密码进行注册")
    public ResponseBean register(@RequestBody MpUser mpUser) {
        if (!StrUtil.isNotEmpty(mpUser.getPassWord())) {
            return ResponseBean.builder().msg("密码不能为空！").build().failed();
        }
        Map<String, Object> claim = BeanUtil.beanToMap(mpUser, false, true);
//        claim.remove("profileId");SecurityUtils.getSubject().isPermitted
        claim.remove("passWord");
        claim.put("roleId", 1);
        TokenUtil tokenUtil = TokenUtilImpl.build(mpUser.getUserName());
        String jwtToken = tokenUtil.generate(claim, NormalConstant.ttlMillis);
        claim.put("token", jwtToken);
        log.warn("Token长度：{}", jwtToken.length());
        mpUser.setToken(jwtToken);
        if (loginService.register(mpUser) > 0) {
            response.setHeader("Authorization", jwtToken);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            return ResponseBean.builder().msg("注册成功！").data(claim).build().success();
        } else {
            return ResponseBean.builder().msg("该账户已存在！请重新输入！").build().success();
        }

    }

    //@ApiImplicitParam(paramType = "header", dataType = "String", name = "Access-Control-Expose-Headers", value = "Authorization", defaultValue = "Authorization", required = true)


    //    @RequiresRoles("[USER]")
//    @RequiresRoles(value = {"[USER:QUERY],[ADMIN:QUERY]"},logical = Logical.OR)
//    @RequiresPermissions(logical = Logical.OR, value = {"[USER:QUERY],[ADMIN:QUERY]"})
    @GetMapping("/online")
    @ResponseBody
    @ApiOperation(value = "当前用户获取", notes = "当前用户获取")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)})
    public ResponseBean online() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (BeanUtil.isNotEmpty(subject)) {
//                subject.isAuthenticated();
                String token = (String) subject.getPrincipal();
                if (StrUtil.isNotBlank(token)) {
                    String account = TokenUtilImpl.build(null).getClaim(token, NormalConstant.ACCOUNT);
                    log.warn(account);
                    return ResponseBean.builder().msg("当前用户获取成功！").data(account).build().success();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBean.builder().msg("当前无用户在线！").build().success();
    }

    @GetMapping("/logout")
    @ApiOperation(value = "用户退出", notes = "用户退出接口")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)})
    public ResponseBean logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            String token = (String) subject.getPrincipal();
            if (StrUtil.isNotBlank(token)) {
                String account = TokenUtilImpl.build(null).getClaim(token, NormalConstant.ACCOUNT);
                if (JedisUtil.exists(NormalConstant.PREFIX_SHIRO_CACHE + account)) {
                    JedisUtil.delKey(NormalConstant.PREFIX_SHIRO_CACHE + account);
                }
                // 清除RefreshToken
                JedisUtil.delKey(NormalConstant.PREFIX_SHIRO_REFRESH_TOKEN + account);
                subject.logout();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("系统异常！");
        }
        return ResponseBean.builder().msg("退出成功！即将返回登录界面......").build().success();
    }
}
