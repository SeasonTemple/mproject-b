package com.seasontemple.mproject.utils.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author Season Temple
 * @program: mproject
 * @description: Jackson配置
 * @create: 2020/03/26 22:43:05
 */
/*
@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper getObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper om = builder.build();
        log.println("injection success?");
        return om;
    }

}*/
