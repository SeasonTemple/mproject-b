package com.seasontemple.mproject.service.service;

import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 用户中心业务层
 */
public interface UserCenterService {

    /**
     * @description: 获取员工所属部门、组信息
     * @return: java.util.Map
     * @author: Season Temple
     */
    Map getBelongTo();

    Map getInformation(String userName);
}
