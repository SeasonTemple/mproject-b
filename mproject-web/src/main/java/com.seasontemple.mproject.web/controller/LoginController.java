package com.seasontemple.mproject.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.service.service.LoginService;
import com.seasontemple.mproject.service.service.MpUserService;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.token.TokenUtil;
import com.seasontemple.mproject.utils.token.TokenUtilImpl;
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
@RestController
@RequestMapping("login")
public class LoginController extends BaseController {

    private static final Log log = LogFactory.get();

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
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username", username);
        chaim.put("password", password);
        chaim.put("roleId", logUser.getRoleId());
        String jwtToken = tokenUtil.generate(chaim);
        chaim.put("token", jwtToken);
        chaim.put("logUser", logUser);
        return ResponseBean.builder().msg("登录成功！").data(chaim).build().success();
    }

    @PostMapping("/register")
    public ResponseBean register(@RequestBody MpUser mpUser) {
        if (loginService.register(mpUser) > 0) {
            TokenUtil tokenUtil = TokenUtilImpl.build(mpUser.getUserName());
            String jwtToken = tokenUtil.generate(BeanUtil.beanToMap(mpUser));
//            HttpServletResponse httpServletResponse = new HttpServletResponse();
            response.setHeader("Authorization", jwtToken);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            return ResponseBean.builder().msg("注册成功！").build().success();
        } else {
            return ResponseBean.builder().msg("该账户已存在！请重新输入！").build().success();
        }
    }

    @GetMapping("/{username}")
    @ResponseBody
    public ResponseBean testdemo(@PathVariable String username) {
        return StrUtil.isNotEmpty(loginService.checkLogin(username).getPassWord()) ? ResponseBean.builder().msg("查找成功！").data(loginService.checkLogin(username)).build().success() :
                ResponseBean.builder().msg("该账号不存在！").data(loginService.checkLogin(username)).build().failed();
    }

}
