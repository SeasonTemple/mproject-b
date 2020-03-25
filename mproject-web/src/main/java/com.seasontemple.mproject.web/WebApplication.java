package com.seasontemple.mproject.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 作为聚合项目的启动类
 * @create: 2019/11/28 15:52:26
 */

@SpringBootApplication(scanBasePackages = "com.seasontemple.mproject")
public class WebApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WebApplication.class, args);
    }
}
