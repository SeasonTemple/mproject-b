package com.seasontemple.mproject.web.handler;

import com.seasontemple.mproject.dao.pojo.TestBean;
import com.seasontemple.mproject.service.DemoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 测试用控制器
 * @create: 2019/11/28 15:55:07
 */

@RestController
@RequestMapping("demo")
@Log4j2
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("test")
    public String test() {
        return demoService.test();
    }

    @GetMapping("/getDetail")
    public Map<String, TestBean> getDetail() {
        Map<String, TestBean> m = new LinkedHashMap<>();
        m.put("t", demoService.getDetail());
        log.info(m);
        return m;

    }

}
