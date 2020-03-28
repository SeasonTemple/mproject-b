package com.seasontemple.mproject.utils.tool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;


/**
 * @author Season Temple
 * @program: mproject
 * @description: 自定义主键生成器
 * @create: 2020/03/27 23:26:22
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator{

    @Override
    public Number nextId(Object entity) {
        return null;
    }

    @Override
    public String nextUUID(Object entity) {
        StringBuffer id2 = new StringBuffer("user-");
        id2.append(IdUtil.objectId());
        Console.log(id2.toString());
        return id2.toString();
    }
}
