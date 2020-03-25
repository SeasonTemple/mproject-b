package com.seasontemple.mproject.web.controller;

import com.seasontemple.mproject.dao.pojo.TestBean;
import com.seasontemple.mproject.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 测试用控制器
 * @create: 2019/11/28 15:55:07
 */

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("test")
    public String test() {
        return demoService.test();
    }

    @GetMapping("/getDetail")
    public TestBean getDetail() {
        TestBean t = demoService.getDetail();
        return t;

    }

}
