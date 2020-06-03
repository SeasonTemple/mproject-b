package com.seasontemple.mproject.service.service;

import com.seasontemple.mproject.dao.entity.MpInformation;
import com.seasontemple.mproject.dao.entity.MpRole;

import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 系统管理业务层
 */
public interface SystemService {

    Map initRoles();

    String modifyRole(MpRole mpRole);

    String addRole(MpRole role);

    String deleteRole(String ids);

    Map initInfo();

    String modifyInfo(MpInformation information);

    String addInfo(MpInformation info);

    String deleteInfo(String ids);
}
