package com.seasontemple.mproject.service.impl;

import com.seasontemple.mproject.dao.pojo.TestBean;
import com.seasontemple.mproject.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 测试用业务层接口实现类
 * @create: 2019/11/28 16:02:56
 */

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String test() {
        return "interface test";
    }

    @Override
    public TestBean getDetail() {
        TestBean t = new TestBean();
        t.setId(1);
        t.setName("face");
        t.setType("2");
        return t;
    }


}
