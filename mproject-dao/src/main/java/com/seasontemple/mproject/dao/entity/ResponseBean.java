package com.seasontemple.mproject.dao.entity;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Bean;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 统一响应数据实体
 * @create: 2020/04/01 12:10:54
 */
@Data
@Accessors(chain = true)
public class ResponseBean {

    private static final Log log = LogFactory.get();
    /**
     * HTTP状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;
}
