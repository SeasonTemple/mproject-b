package com.seasontemple.mproject.dao.mybatis;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 注入公共字段自动填充
 * @create: 2020/04/06 19:54:15
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static Log log = Log.get();

    /**
     * 进行插入操作时：自动填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.warn("{}", (Object) metaObject.getGetterNames());
        // 获取到需要被填充的字段值
        if (metaObject.hasSetter("createTime")) {
            LocalDateTime now = LocalDateTime.now();
            Instant instant = Instant.ofEpochSecond(now.toEpochSecond(ZoneOffset.ofHours(8)));
            Date date = Date.from(instant);
            setFieldValByName("createTime", date, metaObject);
        }
        if (metaObject.hasSetter("lastLogin")) {
            LocalDateTime now = LocalDateTime.now();
            Instant instant = Instant.ofEpochSecond(now.toEpochSecond(ZoneOffset.ofHours(8)));
            Date date = Date.from(instant);
            setFieldValByName("lastLogin", new Date(), metaObject);
        }
        if (metaObject.hasSetter("roleId")) {
            setFieldValByName("roleId", 1, metaObject);
        }
        if (metaObject.hasSetter("status")) {
            setFieldValByName("status", 1, metaObject);
        }
        if (metaObject.hasSetter("profileId")) {
            setFieldValByName("profileId", 0, metaObject);
        }

    }

    /**
     * 更新操作：自动填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.warn("{}", (Object) metaObject.getGetterNames());
        if (metaObject.hasSetter("lastLogin")) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Instant instant = Instant.ofEpochSecond(now.toEpochSecond(ZoneOffset.ofHours(8)));
            Date date = Date.from(instant);
            setFieldValByName("lastLogin", date, metaObject);
        }
    }
}
