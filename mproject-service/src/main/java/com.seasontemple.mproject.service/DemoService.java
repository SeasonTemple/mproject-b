package com.seasontemple.mproject.service;

import com.seasontemple.mproject.dao.entity.TestBean;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 测试用业务层接口
 * @create: 2019/11/28 16:01:48
 */
public interface DemoService {

    String test();

    TestBean getDetail();
}
