package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.dto.EchartsDto;
import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.mapper.UserDetailMapper;
import com.seasontemple.mproject.service.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/05/14 23:58:16
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Override
    public UserDetail initUserDetail(String useName) {
        return new LambdaQueryChainWrapper<>(userDetailMapper).eq(UserDetail::getUserName, useName).last("limit 1").one();
    }

    @Override
    public EchartsDto initChart(String id) {
        return null;
    }
}
