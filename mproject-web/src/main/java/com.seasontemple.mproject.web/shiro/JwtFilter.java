package com.seasontemple.mproject.web.shiro;

import cn.hutool.log.StaticLog;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        StaticLog.warn(" {} 方法被调用.", "isAccessAllowed");
        //这里先让它始终返回false来使用onAccessDenied()方法
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        StaticLog.warn(" {} 方法被调用.", "onAccessDenied");
        //这个地方和前端约定，要求前端将jwtToken放在请求的Header部分
        //所以以后发起请求的时候就需要在Header中放一个Authorization，值就是对应的Token
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        StaticLog.info("请求的 Header 中藏有 jwtToken {}", jwt);
        JwtToken jwtToken = new JwtToken(jwt);
        /*
         * 下面就是固定写法
         * */
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
}
