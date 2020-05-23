package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.entity.MpDepartment;
import com.seasontemple.mproject.dao.entity.MpInformation;
import com.seasontemple.mproject.dao.entity.MpProject;
import com.seasontemple.mproject.dao.mapper.MpDepartmentMapper;
import com.seasontemple.mproject.dao.mapper.MpInformationMapper;
import com.seasontemple.mproject.dao.mapper.MpProjectMapper;
import com.seasontemple.mproject.service.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.seasontemple.mproject.utils.custom.NormalConstant.ALL_USERS;

/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/05/16 23:23:25
 */
@Service
public class UserCenterServiceImpl implements UserCenterService {

    @Autowired
    private MpDepartmentMapper mpDepartmentMapper;

    @Autowired
    private MpProjectMapper mpProjectMapper;

    @Autowired
    private MpInformationMapper mpInformationMapper;

    @Override
    public Map getBelongTo() {
        List<MpDepartment> departmentList = new LambdaQueryChainWrapper<>(mpDepartmentMapper).list();
        List<MpProject> projectList = new LambdaQueryChainWrapper<>(mpProjectMapper).list();
        return MapUtil.builder().put("departments", departmentList).put("projects", projectList).build();
    }

    @Override
    public Map getInformation(String userName) {
//        List<MpInformation> informations = new LambdaQueryChainWrapper<>(mpInformationMapper).list();
        List<MpInformation> informations = new LambdaQueryChainWrapper<>(mpInformationMapper).eq(MpInformation::getReceiver, ALL_USERS).or(i ->i.eq(MpInformation::getReceiver, userName)).list();
//        StaticLog.warn("getInformation imp: {}",informations.size());
        return MapUtil.builder("informations", informations).build();
    }
}
