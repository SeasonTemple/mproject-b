package com.seasontemple.mproject.web.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.seasontemple.mproject.dao.dto.LoginDto;
import com.seasontemple.mproject.dao.dto.PassFindDto;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.dao.group.UserLoginValidatedGroup;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.dao.redis.JedisUtil;
import com.seasontemple.mproject.service.service.LoginService;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.email.EmailSender;
import com.seasontemple.mproject.utils.exception.CustomException;
import com.seasontemple.mproject.utils.token.TokenUtil;
import com.seasontemple.mproject.utils.token.TokenUtilImpl;
import com.seasontemple.mproject.web.shiro.jwt.JwtToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    private final MpUserMapper mpUserMapper;

    private final LoginService loginService;

    public LoginController(MpUserMapper mpUserMapper, LoginService loginService) {
        this.mpUserMapper = mpUserMapper;
        this.loginService = loginService;
    }

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户登录", notes = "使用用户名、密码进行登录")
    @ResponseBody
    public ResponseBean login(@Validated(value = {UserLoginValidatedGroup.class}) LoginDto userDto, BindingResult bindingResult) throws CustomException {
        log.info("LoginDto：{}", userDto);
        if (BeanUtil.isEmpty(userDto)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ResponseBean.builder().msg("登录失败！提交数据非法。").build().success();
        }
        UserRole logUser = loginService.checkLogin(userDto.getUserName());
        boolean isUpdate = updateLoginTime(userDto.getUserName());
        log.warn("{}", isUpdate);
        if (StrUtil.isEmpty(logUser.getPassWord()) || !isUpdate) {
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseBean.builder().msg("登录失败！该账户不存在，请重新输入或前往注册。").build().failed();
        }
        AES aes = SecureUtil.aes(logUser.getSalt());
        String deCryptPwd = aes.decryptStr(logUser.getPassWord(), CharsetUtil.CHARSET_UTF_8);
        log.warn("{}", logUser);
        if (!userDto.getPassWord().equals(deCryptPwd)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseBean.builder().msg("登录失败！密码错误，请重新输入。").build().failed();
        }
        if (logUser.getRoleState().equals(0) || logUser.getAccountStatus().equals(0)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseBean.builder().msg("登录失败！该账户已被禁止使用或拥有的临时角色权限已失效，请联系管理员处理。").build().failed();
        }
        // 清除可能存在的shiro权限信息缓存
/*        if (JedisUtil.exists(NormalConstant.PREFIX_SHIRO_CACHE + logUser.getUserName())) {
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
        claim.put("iat", currentTimeMillis);*/
        String jwtToken = generateToken(logUser);
//        claim.put("token", jwtToken);
        return getResponse(logUser, jwtToken);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "使用用户名、密码进行注册", produces = "application/x-www-form-urlencoded;charset=UTF-8")
    @ResponseBody
    public ResponseBean register(@Validated(value = {UserLoginValidatedGroup.class}) MpUser mpUser, BindingResult bindingResult) {
//        if (!StrUtil.isNotEmpty(mpUser.getPassWord())) {
//            return ResponseBean.builder().msg("密码不能为空！").build().failed();
//        }
        log.warn("{}", mpUser);
        UserRole isExist = loginService.checkLogin(mpUser.getUserName());
        if (!StrUtil.isEmpty(isExist.getPassWord())) {
            return ResponseBean.builder().msg("该账户已存在！请重新输入！").build().success();
        }
        Map<String, Object> claim = BeanUtil.beanToMap(mpUser, false, true);
        log.warn("{}", claim);
        claim.remove("passWord");
        claim.put("roleId", 1);
        Long currentTimeMillis = System.currentTimeMillis();
        claim.put("iat", currentTimeMillis);
        TokenUtil tokenUtil = TokenUtilImpl.build(mpUser.getUserName());
        String jwtToken = tokenUtil.generate(claim, NormalConstant.ttlMillis);
//        claim.put("token", jwtToken);
        log.warn("Token长度：{}", jwtToken.length());
        mpUser.setToken(jwtToken);
        if (loginService.register(mpUser) > 0) {
            UserRole logUser = loginService.checkLogin(mpUser.getUserName());
            loginService.refresh();
            if (StrUtil.isEmpty(logUser.getPassWord())) {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return ResponseBean.builder().msg("注册失败！请重新输入或前往注册。").build().failed();
            }
            log.error("注册成功：{}", logUser);
            return ResponseBean.builder().msg("注册成功！").data(MapUtil.builder("userName", logUser.getUserName()).put("passWord", logUser.getPassWord()).put("token", jwtToken).build()).build().success();
        } else {
            return ResponseBean.builder().msg("该账户已存在！请重新输入！").build().failed();
        }

    }

    @GetMapping("/sso")
    @ResponseBody
    public ResponseBean ssoLogin() {
        if (!getSubject().isAuthenticated()) {
            getSubject().logout();
            response.setHeader("Authorization", null);
            return ResponseBean.builder().msg("自动登录失败！请重新登录！").build().failed();
        }
        String userName = TokenUtilImpl.build(null).getClaim(getSubject().getPrincipal().toString(), NormalConstant.ACCOUNT);
        Long currentTimeMillis = System.currentTimeMillis();
        JedisUtil.setJson(NormalConstant.PREFIX_SHIRO_REFRESH_TOKEN + userName, StrUtil.toString(currentTimeMillis),
                Integer.parseInt(refreshTokenExpireTime));
//        log.warn("{}",getSubject().getPrincipal().toString());
        try {
            JwtToken logToken = new JwtToken(getSubject().getPrincipal().toString());
            getSubject().login(logToken);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("登录失败！获取用户角色或权限失败！");
        }
        response.setHeader("Authorization", getSubject().getPrincipal().toString());
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return ResponseBean.builder().msg("自动登录成功！").data(MapUtil.builder("userName", userName).build()).build().success();

    }

    //@ApiImplicitParam(paramType = "header", dataType = "String", name = "Access-Control-Expose-Headers", value = "Authorization", defaultValue = "Authorization", required = true)


    //    @RequiresRoles(value = {"[USER:QUERY],[ADMIN:QUERY]"},logical = Logical.OR)
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

    @GetMapping("/uLogin")
    @ResponseBody
    public ResponseBean loginByCode(@RequestParam(value = "username") String username) {
        log.warn("{}", username);
        UserRole logUser = loginService.checkLogin(username);
        if (StrUtil.isEmpty(logUser.getPassWord())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseBean.builder().msg("登录失败！该账户不存在，请重新输入或前往注册。").build().failed();
        }
        if (logUser.getRoleState().equals(0) || logUser.getAccountStatus().equals(0)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseBean.builder().msg("登录失败！该账户已被禁止使用或拥有的临时角色权限已失效，请联系管理员处理。").build().failed();
        }
        String token = generateToken(logUser);
        return getResponse(logUser, token);
    }

    private ResponseBean getResponse(UserRole logUser, String token) {
        try {
            JwtToken logToken = new JwtToken(token);
            getSubject().login(logToken);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("登录失败！获取用户角色或权限失败！");
        }
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setStatus(HttpServletResponse.SC_OK);
        return ResponseBean.builder().msg("登录成功！").data(MapUtil.builder("userName", logUser.getUserName()).build()).build().success();
    }

    private Map getResultData(String var, Object value) {
        return MapUtil.builder(var, value).build();
    }

    private boolean updateLoginTime(String userName){
        MpUser user = new MpUser();
        user.setUserName(userName);
        return new LambdaUpdateChainWrapper<>(mpUserMapper).eq(MpUser::getUserName, userName).update(user);
    }


    //    @RequiresRoles(value = {"[USER]", "[CUSTOM]", "[ADMIN]"}, logical = Logical.OR)
//    @RequiresPermissions("[QUERY]")
    @GetMapping(value = "/online")
    @ResponseBody
    @ApiOperation(value = "当前用户获取", notes = "当前用户获取")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)})
    public ResponseBean online() {
        try {
            if (BeanUtil.isNotEmpty(getSubject())) {
                getSubject().isAuthenticated();
                log.warn("账户已登录！");
                String token = getSubject().getPrincipals().toString();
                if (StrUtil.isNotBlank(token)) {
                    String account = TokenUtilImpl.build(null).getClaim(token, NormalConstant.ACCOUNT);
//                    getSubject().checkRole(account);
                    log.warn("{}", account);
                    return ResponseBean.builder().msg("当前用户获取成功！").data(getResultData("userName", account)).build().success();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("该账号没有登录，请先登录");
        }
        return ResponseBean.builder().msg("当前无用户在线！").build().success();
    }

    private String generateToken(UserRole logUser) {
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
        return tokenUtil.generate(claim, NormalConstant.ttlMillis);
    }

    @PostMapping(value = "/forget", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "账户找回", notes = "使用用户名 + 邮箱验证")
    @ResponseBody
    public ResponseBean findOut(@Validated(value = {UserLoginValidatedGroup.class}) PassFindDto userDto, BindingResult bindingResult) throws CustomException {
        log.warn("账户找回：{}", userDto);
        UserRole logUser = loginService.checkLogin(userDto.getUserName());
        if(BeanUtil.isEmpty(logUser)){
            return ResponseBean.builder().msg("该账户不存在！请重新输入").build().failed();
        }else {
            AES aes = SecureUtil.aes(logUser.getSalt());
            String deCryptPwd = aes.decryptStr(logUser.getPassWord(), CharsetUtil.CHARSET_UTF_8);
            return ResponseBean.builder().msg("找回成功！").data(getResultData("passWord",deCryptPwd)).build().success();
        }
    }

    @GetMapping("/getSms")
    @ApiOperation(value = "获取邮箱验证码", notes = "通过邮箱获取验证码")
    @ResponseBody
    public ResponseBean sendCodeByEmail(@RequestParam(value = "email") String email) {
        log.warn("{}", email);
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 38, 8, 3);
        EmailSender.send(email,"账号找回", "您的验证码为："+captcha.getCode()+"，有效时间为30s。");
        return ResponseBean.builder().msg("邮件发送成功").data(captcha.getCode()).build().success();
    }

    @GetMapping("/userRole")
    @ResponseBody
    public ResponseBean userRole() {
        try {
            if (BeanUtil.isNotEmpty(getSubject())) {
                getSubject().isAuthenticated();
                log.warn("账户已登录！");
                String token = getSubject().getPrincipals().toString();
                if (StrUtil.isNotBlank(token)) {
                    String account = TokenUtilImpl.build(null).getClaim(token, NormalConstant.ACCOUNT);
                    log.warn("{}", account);
                    return ResponseBean.builder().msg("当前用户获取成功！").data(loginService.getRole(account)).build().success();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("该账号没有登录，请先登录");
        }
        return ResponseBean.builder().msg("当前无用户在线！").build().success();
    }
}
