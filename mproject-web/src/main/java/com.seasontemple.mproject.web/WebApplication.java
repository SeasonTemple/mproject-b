package com.seasontemple.mproject.web;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Season Temple
 * @program: mproject
 * @description: 作为聚合项目的启动类
 * @create: 2019/11/28 15:52:26
 */
@EnableSwagger2Doc
@SpringBootApplication(scanBasePackages = "com.seasontemple.mproject")
@MapperScan("com.seasontemple.mproject.dao.mapper")
public class WebApplication {

    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enabled", "true");
//        System.setProperty("logging.level.web", "DEBUG ");
        SpringApplication.run(WebApplication.class, args);

    }

}

