package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.entity.*;
import com.seasontemple.mproject.dao.mapper.*;
import com.seasontemple.mproject.service.service.UserCenterService;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import java.io.File;
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

    @Autowired
    private MpRequestMapper mpRequestMapper;

    @Autowired
    private MpUserMapper mpUserMapper;

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
    public Map uploadReport(File file) {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.touch(file));
        List<Map<String, Object>> readAll = reader.readAll();
        StaticLog.warn("uploadReport：{}", readAll);
        return null;
    }

    @Override
    public List<MpReport> downloadReport(String reports) {
        return JSONUtil.parseArray(reports).toList(MpReport.class);
    }

    @Override
    public Map initAuditors() {
        List<MpUser> auditors = mpUserMapper.selectByRole(NormalConstant.ROLE_ID);
        StaticLog.warn("initAuditors：{}", auditors);
        return MapUtil.builder().put("auditors", auditors).build();
    }

    @Override
    public String submitRequest(MpRequest request) {
        return mpRequestMapper.insert(request) == 1 ? "申请提交成功！" : "申请提交失败！";
    }
}
