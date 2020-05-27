package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.entity.*;
import com.seasontemple.mproject.dao.mapper.*;
import com.seasontemple.mproject.service.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class UserCenterServiceImpl implements UserCenterService {

    @Autowired
    private MpDepartmentMapper mpDepartmentMapper;

    @Autowired
    private MpProjectMapper mpProjectMapper;

    @Autowired
    private MpGroupMapper mpGroupMapper;

    @Autowired
    private MpInformationMapper mpInformationMapper;

    @Autowired
    private MpAttendanceMapper mpAttendanceMapper;

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Autowired
    private MpReportMapper mpReportMapper;

    @Override
    public Map getBelongTo() {
        List<MpDepartment> departmentList = new LambdaQueryChainWrapper<>(mpDepartmentMapper).list();
        List<MpGroup> groups = new LambdaQueryChainWrapper<>(mpGroupMapper).list();
        return MapUtil.builder().put("departments", departmentList).put("groups", groups).build();
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

    @Override
    public Map initProjects(String groupId) {
        List<MpProject> projectList = new LambdaQueryChainWrapper<>(mpProjectMapper).eq(MpProject::getGroupId, groupId).list();
        List<UserDetail> members = new LambdaQueryChainWrapper<>(userDetailMapper).select(UserDetail::getId, UserDetail::getRealName, UserDetail::getPosition).eq(UserDetail::getGroupId, groupId).list();
        projectList.forEach(p -> p.setMembers(members));
        return MapUtil.builder().put("projects", projectList).build();
    }

    @Override
    public Map initReports(String userName) {
        List<MpReport> reports = new LambdaQueryChainWrapper<>(mpReportMapper).eq(MpReport::getOwner, userName).orderByAsc(MpReport::getPublish).list();
        reports.forEach(r -> r.setContent(HtmlUtil.unescape(r.getContent())));
        return MapUtil.builder().put("reports", reports).build();
    }

    @Override
    public String submitReport(MpReport mpReport) {
        mpReport.setContent(HtmlUtil.escape(mpReport.getContent()));
        return mpReportMapper.insert(mpReport) == 1 ? "日志提交成功！" : "日志提交失败！";
    }

    @Override
    public String uploadReport(List<MpReport> mpReports) {
        return null;
    }

    @Override
    public String downloadReport() {
        return null;
    }

    @Override
    public String submitRequest(MpRequest mpRequest) {
        return null;
    }
}
