package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.seasontemple.mproject.dao.dto.EchartsDto;
import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.entity.*;
import com.seasontemple.mproject.dao.mapper.*;
import com.seasontemple.mproject.service.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/05/14 23:58:16
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Autowired
    private MpAttendanceMapper mpAttendanceMapper;

    @Autowired
    private MpInformationMapper mpInformationMapper;

    @Autowired
    private MpReportMapper mpReportMapper;

    @Autowired
    private MpProjectMapper mpProjectMapper;

    @Autowired
    private MpProfileMapper mpProfileMapper;


    @Override
    public UserDetail initUserDetail(String userName) {
        UserDetail userDetail = new LambdaQueryChainWrapper<>(userDetailMapper).eq(UserDetail::getUserName, userName).last("limit 1").one();
        List<MpAttendance> attendances = new LambdaQueryChainWrapper<>(mpAttendanceMapper).eq(MpAttendance::getUserId, userDetail.getId()).list();
        userDetail.setAttendance(attendances);
        AES aes = SecureUtil.aes(userDetail.getSalt());
        String deCryptPwd = aes.decryptStr(userDetail.getPassWord(), CharsetUtil.CHARSET_UTF_8);
        userDetail.setPassWord(deCryptPwd);
        return userDetail;
    }

    @Override
    public Map initChart(UserDetail userDetail) {
        List<MpAttendance> attendances = new LambdaQueryChainWrapper<>(mpAttendanceMapper).eq(MpAttendance::getUserId, userDetail.getId()).list();
        int infoCount = new LambdaQueryChainWrapper<>(mpInformationMapper).eq(MpInformation::getPublisher, userDetail.getUserName()).count();
//        int age50 = new LambdaQueryChainWrapper<>(mpProfileMapper).ge(MpProfile::getAge, 50).and(lqc -> lqc.le(MpProfile::getAge, 60)).count();
//        int age40 = new LambdaQueryChainWrapper<>(mpProfileMapper).ge(MpProfile::getAge, 40).and(lqc -> lqc.le(MpProfile::getAge, 50)).count();
//        int age30 = new LambdaQueryChainWrapper<>(mpProfileMapper).ge(MpProfile::getAge, 30).and(lqc -> lqc.le(MpProfile::getAge, 40)).count();
//        int age20 = new LambdaQueryChainWrapper<>(mpProfileMapper).ge(MpProfile::getAge, 20).and(lqc -> lqc.le(MpProfile::getAge, 30)).count();
//        int[] ages = {age20, age30, age40, age50};
        List<MpProfile> profiles = new LambdaQueryChainWrapper<>(mpProfileMapper).list();
        int reports = new LambdaQueryChainWrapper<>(mpReportMapper).eq(MpReport::getOwner, userDetail.getUserName()).count();
        List<MpProject> projects = new LambdaQueryChainWrapper<>(mpProjectMapper).eq(MpProject::getGroupId, userDetail.getGroupId()).list();
        return MapUtil.builder().put("attendances", attendances).put("infoCount", infoCount).put("reports", reports).put("projects", projects).put("profiles",profiles).build();
    }
}
