package com.seasontemple.mproject.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.service.service.LoginService;
import com.seasontemple.mproject.service.service.MpUserService;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.exception.CustomException;
import com.seasontemple.mproject.utils.token.TokenUtil;
import com.seasontemple.mproject.utils.token.TokenUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RestController
@RequestMapping("login")
public class LoginController extends BaseController {

    private static final Log log = Log.get();

    @Autowired
    private LoginService loginService;

    @Autowired
    private MpUserService mpUserService;

//    @Autowired
//    private TokenUtil tokenUtil;

    @PostMapping("/post")
    public ResponseBean login(String username, String password) {
        StaticLog.info("username：{},password：{}", username, password);
        UserRole logUser = loginService.checkLogin(username);
        log.warn("{}", logUser);
        if (!password.equals(logUser.getPassWord())) {
            return ResponseBean.builder().msg("登录失败！该账户不存在").build().failed();
        }
        TokenUtil tokenUtil = TokenUtilImpl.build(username);
        Map<String, Object> claim = new HashMap<>();
        claim.put("userName", username);
        claim.put("passWord", password);
        claim.put("roleId", logUser.getRoleId());
        String jwtToken = tokenUtil.generate(claim, NormalConstant.ttlMillis);
        claim.put("token", jwtToken);
        claim.put("logUser", logUser);
        return ResponseBean.builder().msg("登录成功！").data(claim).build().success();
    }

    @PostMapping("/register")
    public ResponseBean register(@RequestBody MpUser mpUser) {
        if (loginService.register(mpUser) > 0) {
            TokenUtil tokenUtil = TokenUtilImpl.build(mpUser.getUserName());
            Map<String, Object> claim = BeanUtil.beanToMap(mpUser, false, true);
            String jwtToken = tokenUtil.generate(claim, NormalConstant.ttlMillis);
            claim.put("token", jwtToken);
            response.setHeader("Authorization", jwtToken);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            return ResponseBean.builder().msg("注册成功！").data(claim).build().success();
        } else {
            return ResponseBean.builder().msg("该账户已存在！请重新输入！").build().success();
        }

    }

    @GetMapping("/test/{username}")
    @ResponseBody
    public ResponseBean testdemo(@PathVariable String username) {
        return StrUtil.isNotEmpty(loginService.checkLogin(username).getPassWord()) ? ResponseBean.builder().msg("查找成功！").data(loginService.checkLogin(username)).build().success() :
                ResponseBean.builder().msg("该账号不存在！").data(loginService.checkLogin(username)).build().failed();
    }

}
