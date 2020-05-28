package com.seasontemple.mproject.service.service;

import com.seasontemple.mproject.dao.entity.MpAttendance;
import com.seasontemple.mproject.dao.entity.MpReport;
import com.seasontemple.mproject.dao.entity.MpRequest;

import java.io.File;
import java.util.List;
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
     * @return: java.lang.String
     * @author: Season Temple
     */
    String markAttendance(MpAttendance mpAttendance);

    /**
     * @description: 项目组信息初始化
     * @param: [groupId]
     * @return: java.util.Map
     * @author: Season Temple
     */
    Map initProjects(String groupId);

    /**
     * @description: 工作日志初始化
     * @param: [userName]
     * @return: java.util.Map
     * @author: Season Temple
     */
    Map initReports(String userName);

    /**
     * @description: 工作日志提交
     * @param: [mpReport]
     * @return: java.lang.String
     * @author: Season Temple
     */
    String submitReport(MpReport mpReport);

    /**
     * @description: 工作日志上传
     * @param: [file]
     * @return: java.util.Map
     * @author: Season Temple
     */
    Map uploadReport(File file);

    /**
     * @description: 工作日志下载
     * @param: []
     * @return: java.lang.String
     * @author: Season Temple
     */
    List<MpReport> downloadReport(String reports);

    /**
     * @description: 审核人初始化
     * @return: java.util.Map
     * @author: Season Temple
     */
    Map initAuditors();
    /**
     * @description: 事务申请提交
     * @param: [mpRequest]
     * @return: java.lang.String
     * @author: Season Temple
     */
    String submitRequest(MpRequest request);
}
