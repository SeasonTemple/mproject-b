package com.seasontemple.mproject.web.controller;

import cn.hutool.log.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 基础控制器接口，用于预设定制公用方法
 * @create: 2020/04/06 14:32:31
 */
public class BaseController {

    protected static final Log log = Log.get();

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Value("${refreshTokenExpireTime}")
    protected String refreshTokenExpireTime;


    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

    /**
     * 获取当前请求
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 获取项目相对路径
     */
    public String getProjectPath() {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    /**
     * 获取访问客户端IP
     */
    public String getClientIP() {
        if (request == null) {
            return null;
        }
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipAddress;
    }

}
