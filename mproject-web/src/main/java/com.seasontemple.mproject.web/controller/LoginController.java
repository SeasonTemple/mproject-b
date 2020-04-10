package com.seasontemple.mproject.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.log.Log;
import cn.hutool.log.StaticLog;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.dao.redis.JedisUtil;
import com.seasontemple.mproject.service.service.LoginService;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.exception.CustomException;
import com.seasontemple.mproject.utils.token.TokenUtil;
import com.seasontemple.mproject.utils.token.TokenUtilImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("login")
public class LoginController extends BaseController {

    private static final Log log = Log.get();

    @Autowired
    private LoginService loginService;

//    @Autowired
//    private TokenUtil tokenUtil;

    @PostMapping("/post")
    @ApiOperation(value = "用户登录", notes = "使用用户名、密码进行登录")
    public ResponseBean login(String username, String password) throws CustomException {
        StaticLog.info("username：{},password：{}", username, password);
        UserRole logUser = loginService.checkLogin(username);
        AES aes = SecureUtil.aes(logUser.getSalt());
        String deCryptPwd = aes.decryptStr(logUser.getPassWord(), CharsetUtil.CHARSET_UTF_8);
        log.warn("{}", logUser);
        if (!password.equals(deCryptPwd)) {
            return ResponseBean.builder().msg("登录失败！该账户不存在").build().failed();
        }
        // 清除可能存在的shiro权限信息缓存
        if (JedisUtil.exists(NormalConstant.PREFIX_SHIRO_CACHE + username)) {
            JedisUtil.delKey(NormalConstant.PREFIX_SHIRO_CACHE + username);
        }
        // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        JedisUtil.setObject(NormalConstant.PREFIX_SHIRO_REFRESH_TOKEN + username, currentTimeMillis,
                Integer.parseInt(refreshTokenExpireTime));
        TokenUtil tokenUtil = TokenUtilImpl.build(username);
        Map<String, Object> claim = new HashMap<>();
        claim.put("userName", logUser.getUserName());
        claim.put("passWord", logUser.getPassWord());
        claim.put("roleId", logUser.getRoleId());
        String jwtToken = tokenUtil.generate(claim, NormalConstant.ttlMillis);
        claim.put("token", jwtToken);
        return ResponseBean.builder().msg("登录成功！").data(claim).build().success();
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "使用用户名、密码进行注册")
    public ResponseBean register(@RequestBody MpUser mpUser) {
        if (!StrUtil.isNotEmpty(mpUser.getPassWord())) {
            return ResponseBean.builder().msg("密码不能为空！").build().failed();
        }
        Map<String, Object> claim = BeanUtil.beanToMap(mpUser, false, true);
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

    @GetMapping("/test/{username}")
    @ResponseBody
    @RequiresPermissions(logical = Logical.AND, value = {"[USER:EDIT]"})
    @ApiOperation(value = "测试用户是否存在", notes = "使用用户名进行测试")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Access-Control-Expose-Headers", value = "Authorization", defaultValue = "Authorization", required = true)})
    public ResponseBean testdemo(@PathVariable String username) {
        return StrUtil.isNotEmpty(loginService.checkLogin(username).getPassWord()) ? ResponseBean.builder().msg("查找成功！").data(loginService.checkLogin(username)).build().success() :
                ResponseBean.builder().msg("该账号不存在！").data(loginService.checkLogin(username)).build().failed();
    }

}
