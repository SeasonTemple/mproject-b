package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.*;
import com.seasontemple.mproject.dao.mapper.*;
import com.seasontemple.mproject.service.service.HrService;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.token.TokenUtil;
import com.seasontemple.mproject.utils.token.TokenUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description:
 */
@Service
@Transactional
public class HrServiceImpl implements HrService {

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Autowired
    private MpDepartmentMapper mpDepartmentMapper;

    @Autowired
    private MpGroupMapper mpGroupMapper;

    @Autowired
    private MpProjectMapper mpProjectMapper;

    @Autowired
    private MpUserMapper mpUserMapper;

    @Autowired
    private MpProfileMapper mpProfileMapper;

    @Autowired
    private MpRequestMapper mpRequestMapper;

    @Autowired
    private MpAttendanceMapper mpAttendanceMapper;

    @Override
    public Map initUserList() {
        List<UserDetail> userDetails = new LambdaQueryChainWrapper<>(userDetailMapper).list();
        userDetails.forEach(u -> {
            AES aes = SecureUtil.aes(u.getSalt());
            u.setPassWord(aes.decryptStr(u.getPassWord(), CharsetUtil.CHARSET_UTF_8));
        });
        return MapUtil.builder()
                .put("tableData", userDetails)
                .put("departments", new LambdaQueryChainWrapper<>(mpDepartmentMapper).list())
                .put("groups", new LambdaQueryChainWrapper<>(mpGroupMapper).list())
                .put("projects", new LambdaQueryChainWrapper<>(mpProjectMapper).list()).build();
    }

    @Override
    public String addUser(UserDetail userDetail) {
        MpUser mpUser = parseMpUser(userDetail);
        MpProfile profile = parseMpProfile(userDetail);
        LambdaQueryWrapper<MpUser> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(MpUser::getId, mpUser.getId());
        MpUser result = mpUserMapper.selectOne(lambdaQueryWrapper);
        if (!BeanUtil.isEmpty(result) && !BeanUtil.isEmpty(profile)) {
            int k = mpProfileMapper.insert(profile);
            if (k > 0) {
                mpUser.setProfileId(profile.getId());
            }
            int i = mpUserMapper.insert(mpUser);
            return i + k > 1 ? "添加成功！" : "添加失败";
        } else {
            return "添加失败";
        }
    }

    @Override
    public String modifyUser(UserDetail userDetail) {
//        Map<String, Object> user = BeanUtil.beanToMap(userDetail);
//        MpUser mpUser = BeanUtil.mapToBean(user, MpUser.class, true);
//        AES aes = SecureUtil.aes(mpUser.getSalt());
//        mpUser.setPassWord(aes.encryptHex(mpUser.getPassWord()));
//        StaticLog.warn("modifyUser mpUser:{}", mpUser);
//        user.remove("id");
//        MpProfile profile = BeanUtil.mapToBean(user, MpProfile.class, true);
//        StaticLog.warn("modifyUser profile:{}", profile);
        MpUser mpUser = parseMpUser(userDetail);
        MpProfile profile = parseMpProfile(userDetail);
        if (!BeanUtil.isEmpty(mpUser) && !BeanUtil.isEmpty(profile)) {
            int i = new LambdaUpdateChainWrapper<>(mpUserMapper).eq(MpUser::getId, mpUser.getId()).update(mpUser) ? 1 : 0;
            LambdaUpdateWrapper<MpProfile> wrapper = Wrappers.lambdaUpdate();
            wrapper.eq(MpProfile::getIdNumber, profile.getIdNumber());
            int k = mpProfileMapper.update(profile, wrapper);
            return i + k >= 1 ? "更新成功！" : "更新失败！";
        } else if (!BeanUtil.isEmpty(mpUser)) {
            return mpUserMapper.updateById(mpUser) > 0 ? "更新成功！" : "更新失败！";
        } else {
            LambdaUpdateWrapper<MpProfile> wrapper = Wrappers.lambdaUpdate();
            wrapper.eq(MpProfile::getIdNumber, profile.getIdNumber());
            int k = mpProfileMapper.update(profile, wrapper);
            return k > 0 ? "更新成功！" : "更新失败！";
        }
    }

    @Override
    public String deleteUsers(String ids) {
        String target = ids.replace("ids=", "");
        if (target.contains("&")) {
            List<String> idList = CollUtil.toList(ids.replace("ids=", "").split("&"));
            return mpUserMapper.deleteBatchIds(idList) > 0 ? "删除成功！" : "删除失败！";
        } else {
            return mpUserMapper.deleteById(target) > 0 ? "删除成功！" : "删除失败！";
        }
    }

    @Override
    public Map initDuty() {
        return MapUtil.builder()
                .put("departments", new LambdaQueryChainWrapper<>(mpDepartmentMapper).list())
                .put("groups", new LambdaQueryChainWrapper<>(mpGroupMapper).list())
                .put("projects", new LambdaQueryChainWrapper<>(mpProjectMapper).list()).build();
    }

    @Override
    public String addDuty(int type, String dutyDto) {
        if (type == 1) {
            MpDepartment department = JSONUtil.toBean(dutyDto, MpDepartment.class);
            return mpDepartmentMapper.insert(department) > 0 ? "部门信息添加成功！" : "部门信息添加失败！";
        } else if (type == 2) {
            MpGroup group = JSONUtil.toBean(dutyDto, MpGroup.class);
            return mpGroupMapper.insert(group) > 0 ? "组信息添加成功！" : "组信息添加失败！";
        } else {
            MpProject project = JSONUtil.toBean(dutyDto, MpProject.class);
            return mpProjectMapper.insert(project) > 0 ? "项目信息添加成功！" : "项目添加失败！";
        }
    }

    @Override
    public String modifyDuty(int type, String dutyDto) {
        if (type == 1) {
            MpDepartment department = JSONUtil.toBean(dutyDto, MpDepartment.class);
            return mpDepartmentMapper.updateById(department) > 0 ? "部门信息更新成功！" : "部门信息更新失败！";
        } else if (type == 2) {
            MpGroup group = JSONUtil.toBean(dutyDto, MpGroup.class);
            return mpGroupMapper.updateById(group) > 0 ? "组信息更新成功！" : "组信息更新失败！";
        } else {
            MpProject project = JSONUtil.toBean(dutyDto, MpProject.class);
            return mpProjectMapper.updateById(project) > 0 ? "项目信息更新成功！" : "项目更新失败！";
        }
    }

    @Override
    public String deleteDuty(Integer id) {
        return null;
    }

    @Override
    public Map initRequest() {
        return MapUtil.builder("requests", new LambdaQueryChainWrapper<>(mpRequestMapper).list()).build();
    }

    @Override
    public String handleRequest(int[] ids) {
        return null;
    }

    @Override
    public Map initSalary() {
        return MapUtil.builder("salaryList", new LambdaQueryChainWrapper<>(mpAttendanceMapper).list()).build();
    }

    @Override
    public String modifySalary(Integer id) {
        return null;
    }

    private MpUser parseMpUser(UserDetail userDetail) {
        Map<String, Object> user = BeanUtil.beanToMap(userDetail);
        MpUser mpUser = BeanUtil.mapToBean(user, MpUser.class, true);
        AES aes = SecureUtil.aes(mpUser.getSalt());
        mpUser.setPassWord(aes.encryptHex(mpUser.getPassWord()));
        StaticLog.warn("parseMpUser mpUser:{}", mpUser);
        return mpUser;
    }

    private MpProfile parseMpProfile(UserDetail userDetail) {
        Map<String, Object> user = BeanUtil.beanToMap(userDetail);
        user.remove("id");
        MpProfile profile = BeanUtil.mapToBean(user, MpProfile.class, true);
        StaticLog.warn("modifyUser profile:{}", profile);
        return profile;
    }
}
