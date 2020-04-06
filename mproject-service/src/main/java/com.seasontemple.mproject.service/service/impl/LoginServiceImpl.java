package com.seasontemple.mproject.service.service.impl;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.dao.mapper.UserRoleMapper;
import com.seasontemple.mproject.service.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Optional;


/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/04/05 01:28:24
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private static Log log = LogFactory.get(LoginServiceImpl.class);

    @Autowired
    private MpUserMapper mpUserMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRole checkLogin(String username) {
//        LambdaQueryWrapper<MpUser> queryWrapper = Wrappers.lambdaQuery();
//        MpUser logUser = mpUserMapper.selectOne(queryWrapper.select(MpUser::getId, MpUser::getUserName, MpUser::getPassWord).eq(MpUser::getUserName, username));
//        log.warn("{}", logUser);
        return Optional.ofNullable(new LambdaQueryChainWrapper<>(userRoleMapper)
                .select(UserRole::getId, UserRole::getUserName, UserRole::getPassWord,UserRole::getRoleId)
                .eq(UserRole::getUserName, username)
                .last("limit 1").one()).orElse(new UserRole());
    }

    @Override
    public int register(MpUser mpUser) {
        return mpUserMapper.insert(mpUser);
    }
}
