package com.seasontemple.mproject.web.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.NumberUtil;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.custom.ResultCode;
import com.seasontemple.mproject.utils.exception.CustomException;
import com.sun.deploy.net.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 各类辅助接口集
 * @create: 2020/04/14 20:48:52
 */
@RestController
public class ToolController extends BaseController implements AutoCloseable{

    @GetMapping("/getVCode")
    @ResponseBody
    public ResponseBean getCode() {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setContentType("image/gif");
            response.setStatus(HttpServletResponse.SC_OK);
            //定义图形验证码的长和宽
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 38, 6, 3);
            // 自定义验证码内容为四则运算方式
//            captcha.setGenerator(new MathGenerator());
            return ResponseBean.builder().data(MapUtil.builder().put("codeImg", captcha.getImageBytes()).put("codeResult", captcha.getCode()).build()).build().success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("获取验证码异常");
        }
    }

    @Override
    public void close() throws Exception {
    }
}
