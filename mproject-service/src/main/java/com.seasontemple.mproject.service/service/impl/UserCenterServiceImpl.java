package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.map.MapUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.entity.*;
import com.seasontemple.mproject.dao.mapper.*;
import com.seasontemple.mproject.service.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private MpAttendanceMapper mpAttendanceMapper;

    @Override
    public Map getBelongTo() {
        List<MpDepartment> departmentList = new LambdaQueryChainWrapper<>(mpDepartmentMapper).list();
        List<MpProject> projectList = new LambdaQueryChainWrapper<>(mpProjectMapper).list();
        return MapUtil.builder().put("departments", departmentList).put("projects", projectList).build();
    }

    @Override
    public Map getInformation(String userName) {
//        List<MpInformation> informations = new LambdaQueryChainWrapper<>(mpInformationMapper).list();
        List<MpInformation> informations = new LambdaQueryChainWrapper<>(mpInformationMapper).eq(MpInformation::getReceiver, ALL_USERS).or(i -> i.eq(MpInformation::getReceiver, userName)).list();
//        StaticLog.warn("getInformation imp: {}",informations.size());
        return MapUtil.builder("informations", informations).build();
    }

    @Override
    public Map initAttendance(String userId) {
//        ZonedDateTime zonedDateTime = LocalDate.now().atStartOfDay(ZoneId.systemDefault());
//        Instant instant1 = zonedDateTime.toInstant();
//        Date from = Date.from(instant1);
        List<MpAttendance> attendances = new LambdaQueryChainWrapper<>(mpAttendanceMapper).eq(MpAttendance::getUserId, userId).list();
//        MpAttendance toDay = attendances.stream().filter(a->a.getDay().equals(from)).findAny().orElse(new MpAttendance());
        return MapUtil.builder().put("attendances", attendances).build();
    }

    @Override
    public String markAttendance(MpAttendance mpAttendance) {
        return mpAttendanceMapper.insert(mpAttendance) == 1 ? "签到信息同步成功！" : "签到信息同步失败！";
    }
}
