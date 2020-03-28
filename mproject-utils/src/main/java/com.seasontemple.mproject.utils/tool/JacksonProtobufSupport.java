package com.seasontemple.mproject.utils.tool;

import cn.hutool.core.lang.Console;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Season Temple
 * @program: mproject
 * @description: Jackson with Protobuf
 * @create: 2020/03/27 19:35:30
 */
@Configuration
public class JacksonProtobufSupport {

    @Bean
    @Primary
    @SuppressWarnings("unchecked")
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        Console.log("jackson2ObjectMapperBuilderCustomizer");
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.featuresToDisable(
                    JsonGenerator.Feature.IGNORE_UNKNOWN,
                    MapperFeature.DEFAULT_VIEW_INCLUSION,
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
            );
            jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
                    .dateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"))
                    .defaultViewInclusion(true)
                    .serializationInclusion(JsonInclude.Include.NON_NULL)
                    .modulesToInstall(ProtobufModule.class);
        };
    }

}