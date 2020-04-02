package com.seasontemple.mproject.web.shiro;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puppycrawl.tools.checkstyle.utils.CommonUtils;
import com.seasontemple.mproject.dao.redis.JedisUtil;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Season Temple
 * @program: mproject
 * @description: Shiro-JWT拦截器，拦截
 * 自定义一个Filter，用来拦截所有的请求判断是否携带Token
 * isAccessAllowed()判断是否携带了有效的JwtToken
 * onAccessDenied()是没有携带JwtToken的时候进行账号密码登录，登录成功允许访问，登录失败拒绝访问
 * @create: 2020/03/29 21:24:51
 */
public class JwtFilter extends AccessControlFilter {

    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Map<String, String> whiteList = new HashMap<>();
        whiteList.put("druid", "druid");
        whiteList.put("swagger", "swagger");
        whiteList.put("v2", "v2");
        whiteList.put("testdemo", "testdemo");
        boolean res = whiteList.values().stream().anyMatch(w -> WebUtils.getRequestUri((HttpServletRequest) request).contains(w));
        StaticLog.warn("拦截到的请求体为: {}，匹配结果为：{}", WebUtils.getRequestUri((HttpServletRequest) request), res);
        return res;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
//        StaticLog.warn(" {} 方法被调用.", "isAccessAllowed");
        //这里先让它始终返回false来使用onAccessDenied()方法
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        StaticLog.warn(" {} 方法被调用.", "onAccessDenied");
        //这个地方和前端约定，要求前端将jwtToken放在请求的Header部分
        //所以以后发起请求的时候就需要在Header中放一个Authorization，值就是对应的Token
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        StaticLog.warn("该请求的 Header 中 {}", !StrUtil.isNotEmpty(jwt) ? "没有token存在！登录异常！" : "的 token 为：" + jwt);
        JwtToken jwtToken = new JwtToken(jwt);

        try {
            // 委托 realm 进行登录认证
            //所以这个地方最终还是调用JwtRealm进行的认证
            getSubject(servletRequest, servletResponse).login(jwtToken);
            //也就是subject.login(token)
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(servletResponse);
            //调用下面的方法向客户端返回错误信息
            return false;
        }

        return true;
        //执行方法中没有抛出异常就表示登录成功
    }

    //登录失败时默认返回 401 状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("登录失败！");
    }

    /**
     * 刷新AccessToken，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     */
    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        // 拿到当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
        // 获取当前Token的帐号信息
        // 判断Redis中RefreshToken是否存在
        // Redis中RefreshToken还存在，获取RefreshToken的时间戳
        // 获取当前AccessToken中的时间戳，与RefreshToken的时间戳对比，如果当前时间戳一致，进行AccessToken刷新
        // 获取当前最新时间戳
//                String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        // 读取配置文件，获取refreshTokenExpireTime属性
        // PropertiesUtil.readProperties("config.properties");
        // String refreshTokenExpireTime =
        // PropertiesUtil.getProperty("refreshTokenExpireTime");
        // 设置RefreshToken中的时间戳为当前最新时间戳，且刷新过期时间重新为30分钟过期(配置文件可配置refreshTokenExpireTime属性)
        // 刷新AccessToken，设置时间戳为当前最新时间戳
        // 将新刷新的AccessToken再次进行Shiro的登录
        // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获，如果没有抛出异常则代表登入成功，返回true
        // 最后将刷新的AccessToken存放在Response的Header中的Authorization字段返回
//                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//                httpServletResponse.setHeader("Authorization", token);
//                httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
//                return true;
//            }
//        }
        return false;
    }
}
