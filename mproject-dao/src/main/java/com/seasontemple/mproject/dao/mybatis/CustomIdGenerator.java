package com.seasontemple.mproject.dao.mybatis;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.stereotype.Component;

import java.sql.Statement;


/**
 * @author Season Temple
 * @program: mproject
 * @description: 自定义主键生成器
 * @create: 2020/03/27 23:26:22
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    /**
     * @description: 生成数字类型的随机主键ID
     * @param: [entity]
     * @return: java.lang.Number
     */
    @Override
    public Number nextId(Object entity) {
        String id = IdUtil.objectId();
        return NumberUtil.binaryToLong(id);
    }

    /**
     * @description: 获得前缀为user-的基于UUID生成的主键ID
     * @param: [entity]
     * @return: java.lang.String
     */
    @Override
    public String nextUUID(Object entity) {
        StringBuffer tableId = new StringBuffer("user-");
        tableId.append(IdUtil.objectId());
//        Console.log(tableId.toString());
        return tableId.toString();
    }
}
