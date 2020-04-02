package com.seasontemple.mproject.web.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.token.TokenUtil;
import com.seasontemple.mproject.utils.token.TokenUtilImpl;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
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
@RequestMapping
public class LoginController {

    private static final Log log = LogFactory.get();

    @PostMapping(value = "/login/{username}{password}")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        StaticLog.info("username：{},password：{}", username, password);
        Map<String, String> map = new HashMap<>();
        if (!"demoData".equals(username) || !"demoData".equals(password)) {
            map.put("msg", "用户名密码错误");
            return ResponseEntity.ok(map);
        }
        TokenUtil tokenUtil = TokenUtilImpl.build("李茂杉", SignatureAlgorithm.HS256);
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username", username);
        chaim.put("password", password);
        chaim.put("roleID", "1");
        String jwtToken = tokenUtil.generate(chaim, 5 * 60 * 1000);
        map.put("msg", "登录成功");
        map.put("token", jwtToken);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/testdemo")
    public ResponseBean testdemo() {
        return ResponseBean.builder().msg("TestDemo").build().OK();
    }
}
