package com.seasontemple.mproject.service.service;

import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.MpUser;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 登录业务层接口
 * @create: 2020/04/05 01:05:03
 */
public interface LoginService {

    /**
     * @description: 获得对应用户基本信息
     * @param username
     * @return MpUser
     */
    UserRole checkLogin(String username);

    /**
     * @description: 用户注册
     * @param: mpUser
     * @return: boolean
     */
    int register(MpUser mpUser);

}
