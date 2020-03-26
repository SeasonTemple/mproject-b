package com.seasontemple.test;

import com.seasontemple.mproject.utils.pojo.RequestMeta;
import com.seasontemple.mproject.utils.tool.BaseHandler;
import com.seasontemple.mproject.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 单元测试Utils模块
 * @create: 2020/03/22 18:36:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WebAppConfiguration("src/main/resource")
public class TestApplication {

    @Autowired
    List<BaseHandler> baseHandlers;

    public void testController() {
        baseHandlers.forEach(System.out::println);
    }

    @Test
    public void findSome() {
        Supplier<RequestMeta> initializr = ()-> RequestMeta.builder().name("尼玛").content("你妈好").code(11).build();
        System.out.println(initializr.get());
    }

    @Test
    public void ccc() {
        testController();
    }
}
