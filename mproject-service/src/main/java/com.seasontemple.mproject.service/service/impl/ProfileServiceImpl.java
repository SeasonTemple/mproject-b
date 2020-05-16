package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.entity.MpDepartment;
import com.seasontemple.mproject.dao.entity.MpProject;
import com.seasontemple.mproject.dao.mapper.MpDepartmentMapper;
import com.seasontemple.mproject.dao.mapper.MpProjectMapper;
import com.seasontemple.mproject.service.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 个人中心业务层实现
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private MpDepartmentMapper mpDepartmentMapper;

    @Autowired
    private MpProjectMapper mpProjectMapper;

    @Override
    public Map getBelongTo() {
        List<MpDepartment> departmentList = new LambdaQueryChainWrapper<>(mpDepartmentMapper).list();
        List<MpProject> projectList = new LambdaQueryChainWrapper<>(mpProjectMapper).list();
        return MapUtil.builder().put("departments", departmentList).put("projects", projectList).build();
    }
}
