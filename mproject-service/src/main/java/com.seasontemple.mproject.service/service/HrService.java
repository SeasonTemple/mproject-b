package com.seasontemple.mproject.service.service;

import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.entity.MpProfile;
import com.seasontemple.mproject.dao.entity.MpRequest;
import com.seasontemple.mproject.dao.entity.MpUser;

import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 人事管理业务接口层
 */
public interface HrService {
    /**
     * @description: 用户列表初始化
     * @return: java.util.Map
     * @author: Season Temple
     */
    Map initUserList();

    String addUser(UserDetail userDetail);

    String modifyUser(UserDetail userDetail);

    String deleteUsers(String ids);

    Map initDuty();

    String addDuty(int type, String dutyDto);

    String modifyDuty(int type, String dutyDto);

    String deleteDuty(Integer id);

    Map initRequest();

    String handleRequest(String requests);

    Map initSalary();

    String modifySalary(MpProfile profile);
}
