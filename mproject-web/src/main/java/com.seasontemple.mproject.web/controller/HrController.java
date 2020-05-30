package com.seasontemple.mproject.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.seasontemple.mproject.dao.dto.DutyDto;
import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.entity.MpDepartment;
import com.seasontemple.mproject.dao.group.UserCenterValidateGroup;
import com.seasontemple.mproject.service.service.HrService;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.security.util.ArrayUtil;

import java.util.List;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 人事管理接口控制层
 */
@Api(value = "人事管理接口", tags = "人事管理接口控制层")
@RestController
public class HrController extends BaseController {

    @Autowired
    private HrService hrService;

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/initUserList", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "职工列表初始化、筛选信息初始化", notes = "职工列表初始化、筛选信息初始化API")
    @ResponseBody
    public ResponseBean initUserList() throws CustomException {
        return ResponseBean.builder().msg("用户列表初始化、筛选信息初始化成功！").data(hrService.initUserList()).build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/addUser", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "职工信息修改", notes = "职工信息修改API")
    @ResponseBody
    public ResponseBean addUser(@Validated(value = {UserCenterValidateGroup.class}) UserDetail userDetail, BindingResult bindingResult) throws CustomException {
        log.warn("modifyUser: {}", userDetail);
        return ResponseBean.builder().msg(hrService.modifyUser(userDetail)).build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/modifyUser", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "职工信息修改", notes = "职工信息修改API")
    @ResponseBody
    public ResponseBean modifyUser(@Validated(value = {UserCenterValidateGroup.class}) UserDetail userDetail, BindingResult bindingResult) throws CustomException {
        log.warn("modifyUser: {}", userDetail);
        return ResponseBean.builder().msg(hrService.modifyUser(userDetail)).build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @DeleteMapping(value = "/deleteUsers", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "职工（批量）删除", notes = "职工（批量）删除API")
    @ResponseBody
    public ResponseBean deleteUsers(@RequestParam("0") String ids) throws CustomException {
        log.warn("deleteUsers: {}", ids);
        return ResponseBean.builder().msg(hrService.deleteUsers(ids)).build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/initDuty", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "职能信息初始化", notes = "职能信息初始化API")
    @ResponseBody
    public ResponseBean initDuty() throws CustomException {
        return ResponseBean.builder().msg("职能信息初始化成功！").data(hrService.initDuty()).build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/addDuty", produces = {"application/json;charset=UTF-8", "application/x-www-form-urlencoded;charset=UTF-8"})
    @ApiOperation(value = "职能信息新增", notes = "职能信息新增API")
    @ResponseBody
    public ResponseBean addDuty(@RequestParam int type, @RequestParam String dutyDto) throws CustomException {
        log.warn("addDuty: {}/{}", type, dutyDto);
        return ResponseBean.builder().msg(hrService.addDuty(type, dutyDto)).build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/modifyDuty", produces = {"application/json;charset=UTF-8", "application/x-www-form-urlencoded;charset=UTF-8"})
    @ApiOperation(value = "职能信息修改", notes = "职能信息修改API")
    @ResponseBody
    public ResponseBean modifyDuty(@RequestParam int type, @RequestParam String dutyDto) throws CustomException {
        log.warn("modifyDuty: {}{}", type, dutyDto);
        return ResponseBean.builder().msg(hrService.modifyDuty(type, dutyDto)).build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/deleteDuty", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "职能信息删除", notes = "职能信息删除API")
    @ResponseBody
    public ResponseBean deleteDuty() throws CustomException {
        return ResponseBean.builder().msg("职能信息删除成功！").build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/initRequest", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "申请信息初始化", notes = "申请信息初始化API")
    @ResponseBody
    public ResponseBean initRequest() throws CustomException {
        return ResponseBean.builder().msg("申请信息初始化成功！").data(hrService.initRequest()).build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/handleRequest", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "申请信息审核", notes = "申请信息审核API")
    @ResponseBody
    public ResponseBean handleRequest() throws CustomException {
        return ResponseBean.builder().msg("申请信息审核成功！").build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/initSalary", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "薪资信息初始化", notes = "薪资信息初始化API")
    @ResponseBody
    public ResponseBean initSalary() throws CustomException {
        return ResponseBean.builder().msg("薪资信息初始化成功！").data(hrService.initSalary()).build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/modifySalary", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "薪资信息调整", notes = "薪资信息调整API")
    @ResponseBody
    public ResponseBean modifySalary() throws CustomException {
        return ResponseBean.builder().msg("薪资信息调整成功！").build().success();
    }
}
