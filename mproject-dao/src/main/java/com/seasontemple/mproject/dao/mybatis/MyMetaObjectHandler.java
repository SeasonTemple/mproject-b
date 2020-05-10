package com.seasontemple.mproject.dao.mybatis;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
            setFieldValByName("createTime", new Date(), metaObject);
        }
        if (metaObject.hasSetter("lastLogin")) {
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
        if (metaObject.hasSetter("lastLogin")) {
            setFieldValByName("lastLogin", LocalDateTime.now(), metaObject);
        }
    }
}
