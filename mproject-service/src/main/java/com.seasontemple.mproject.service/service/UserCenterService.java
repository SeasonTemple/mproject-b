package com.seasontemple.mproject.service.service;

import com.seasontemple.mproject.dao.entity.MpAttendance;

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

    /**
     * @description: 系统消息初始化
     * @param: [userName]
     * @return: java.util.Map
     * @author: Season Temple
     */
    Map getInformation(String userName);

    /**
     * @description: 当月考勤信息初始化
     * @param: [userId]
     * @return: java.util.Map
     * @author: Season Temple
     */
    Map initAttendance(String userId);

    /**
     * @description: 签到考勤信息记录
     * @param: [mpAttendance]
     * @return: int
     * @author: Season Temple
     */
    String markAttendance(MpAttendance mpAttendance);
}
