package com.seasontemple.mproject.utils.pojo;

import lombok.Builder;
import lombok.ToString;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 测试用信息实体
 * @create: 2020/03/22 16:46:53
 */
@Builder
@ToString
public class RequestMeta {

    private String name;
    private String content;
    private int code;
}
