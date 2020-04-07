package com.seasontemple.mproject.web.shiro.jwt;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.StaticLog;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seasontemple.mproject.dao.redis.JedisUtil;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.exception.CustomException;
import com.seasontemple.mproject.utils.token.TokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Season Temple
 * @program: mproject
 * @description: Shiro-JWT拦截器，拦截
 * 自定义一个Filter，用来拦截所有的请求判断是否携带Token
 * isAccessAllowed()判断是否携带了有效的JwtToken
 * onAccessDenied()是没有携带JwtToken的时候进行账号密码登录，登录成功允许访问，登录失败拒绝访问
 * @create: 2020/03/29 21:24:51
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    @Autowired
    private TokenUtil tokenUtil;

    private static final Log log = Log.get();

    /*@Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Map<String, String> whiteList = new HashMap<>();
        whiteList.put("druid", "druid");
        whiteList.put("swagger", "swagger");
        whiteList.put("v2", "v2");
        whiteList.put("login", "login");
        StaticLog.warn("拦截到的请求体为: {}，匹配结果为：{}", WebUtils.getRequestUri((HttpServletRequest) request), request);
        return whiteList.values().stream().anyMatch(w -> WebUtils.getRequestUri((HttpServletRequest) request).contains(w));
    }*/

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
      /*  HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }*/
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) {
//        StaticLog.warn(" {} 方法被调用.", "isAccessAllowed");
        // 判断用户是否想要登入
        if (this.isLoginAttempt(request, response)) {
            try {
                // 进行Shiro的登录UserRealm
                this.executeLogin(request, response);
            } catch (Exception e) {
                // 认证出现异常，传递错误信息msg
                String msg = e.getMessage();
                // 获取应用异常(该Cause是导致抛出此throwable(异常)的throwable(异常))
                Throwable throwable = e.getCause();
                if (throwable instanceof SignatureVerificationException) {
                    // 该异常为JWT的AccessToken认证失败(Token或者密钥不正确)
                    msg = "token或者密钥不正确(" + throwable.getMessage() + ")";
                } else if (throwable instanceof TokenExpiredException) {
                    // 该异常为JWT的AccessToken已过期，判断RefreshToken未过期就进行AccessToken刷新
                    if (this.refreshToken(request, response)) {
                        return true;
                    } else {
                        msg = "token已过期(" + throwable.getMessage() + ")";
                    }
                } else {
                    // 应用异常不为空
                    if (throwable != null) {
                        // 获取应用异常msg
                        msg = throwable.getMessage();
                    }
                }
                /**
                 * 错误两种处理方式 1. 将非法请求转发到/401的Controller处理，抛出自定义无权访问异常被全局捕捉再返回Response信息 2.
                 * 无需转发，直接返回Response信息 一般使用第二种(更方便)
                 */
                // 直接返回Response信息
                this.onLoginFail(response, msg);
                return false;
            }
        }
        return true;
    }

    /**
     * 将executeLogin方法调用去除，如果没有去除将会循环调用doGetAuthenticationInfo方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        StaticLog.warn(" {} 方法被调用.", "onAccessDenied");
        //这个地方和前端约定，要求前端将jwtToken放在请求的Header部分
        //所以以后发起请求的时候就需要在Header中放一个Authorization，值就是对应的Token
       /* HttpServletRequest request = (HttpServletRequest) servletRequest;
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
        }*/
        //执行方法中没有抛出异常就表示登录成功
        this.sendChallenge(request, response);
        return false;
    }

    /**
     * 检测Header里面是否包含Authorization字段，有就进行Token登录认证授权
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        // 拿到当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
//        String token = this.getAuthzHeader(request);
        return true;
    }

     // 进行AccessToken登录认证授权
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        // 拿到当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
        JwtToken token = new JwtToken(this.getAuthzHeader(request));
        // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获
        this.getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


    //登录失败时默认返回 401 状态码
    private void onLoginFail(ServletResponse response, String msg) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        try (PrintWriter out = httpResponse.getWriter()) {
            String data = om.writeValueAsString(ResponseBean.builder().code(HttpStatus.UNAUTHORIZED.value()).msg( "无权访问(Unauthorized):" + msg).build());
            out.append(data);
        } catch (IOException e) {
            log.error("返回Response信息出现IOException异常：{}", e.getMessage());
            throw new CustomException("返回Response信息出现IOException异常："+ e.getMessage());
        }

    }

    /**
     * 刷新AccessToken，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     */
    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        // 拿到当前Header中Authorization的AccessToken(Shiro中getAuthzHeader方法已经实现)
        String token = this.getAuthzHeader(request);
        Claims claims = tokenUtil.parse(token);
        claims.remove("expiration");
        // 获取当前Token的帐号信息
        String account = claims.getAudience();
        // 判断Redis中RefreshToken是否存在
        if (JedisUtil.exists(NormalConstant.PREFIX_SHIRO_REFRESH_TOKEN + account)) {
            // Redis中RefreshToken还存在，获取RefreshToken的时间戳
            String currentTimeMillisRedis = Objects.requireNonNull(JedisUtil.getObject(NormalConstant.PREFIX_SHIRO_REFRESH_TOKEN + account)).toString();
            // 获取当前AccessToken中的时间戳，与RefreshToken的时间戳对比，如果当前时间戳一致，进行AccessToken刷新
            if (tokenUtil.getClaim(token, NormalConstant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                // 获取当前最新时间戳
                long nowMillis = System.currentTimeMillis();
                String currentTimeMillis = String.valueOf(nowMillis);
                // 读取配置文件，获取refreshTokenExpireTime属性
//                PropertiesUtil.readProperties("config.properties");
//                String refreshTokenExpireTime = PropertiesUtil.getProperty("refreshTokenExpireTime");
                // 设置RefreshToken中的时间戳为当前最新时间戳，且刷新过期时间重新为30分钟过期(配置文件可配置refreshTokenExpireTime属性)
                JedisUtil.setObject(NormalConstant.PREFIX_SHIRO_REFRESH_TOKEN + account, currentTimeMillis, Integer.parseInt(refreshTokenExpireTime));
                // 刷新AccessToken，设置时间戳为当前最新时间戳
//                token = tokenUtil.generate(account, currentTimeMillis);
                /*Map<String, Object> claim = new HashMap<>();
                claims.forEach((s, o) -> {
                    if (!s.equals("expiration")) claim.put(s, o);
                });
                BeanUtil.*/
                token = tokenUtil.generate(claims, NormalConstant.ttlMillis);
                // 将新刷新的AccessToken再次进行Shiro的登录
                JwtToken jwtToken = new JwtToken(token);
                // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获，如果没有抛出异常则代表登入成功，返回true
                this.getSubject(request, response).login(jwtToken);
                // 最后将刷新的AccessToken存放在Response的Header中的Authorization字段返回
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                httpServletResponse.setHeader("Authorization", token);
                httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
                return true;
            }
        }
        return false;
    }
}
