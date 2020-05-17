package com.seasontemple.mproject.web.controller;

import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.group.UserLoginValidatedGroup;
import com.seasontemple.mproject.service.service.UserCenterService;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 用户个人中心接口控制层
 * @create: 2020/04/29 00:12:12
 */
@Api(value = "用户个人中心接口", tags = "用户个人中心接口控制层")
@RestController
public class UserCenterController extends BaseController {

    @Autowired
    private UserCenterService userCenterService;

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/modifyDetail", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户详细信息初始化", notes = "用户详细信息初始化获取接口")
    @ResponseBody
    public ResponseBean modifyDetail(@Validated(value = {UserLoginValidatedGroup.class}) UserDetail userDetail, BindingResult bindingResult) throws CustomException {
        log.warn("modifyDetail: {}", userDetail);
        return ResponseBean.builder().msg("数据提交成功！").build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/belongTo")
    @ApiOperation(value = "职能选项初始化", notes = "用户职能选项初始化获取接口")
    @ResponseBody
    public ResponseBean belongTo() throws CustomException {
        return ResponseBean.builder().msg("获取部门、组信息成功！").data(userCenterService.getBelongTo()).build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/submitReport")
    @ApiOperation(value = "提交工作报告", notes = "提交工作报告接口")
    @ResponseBody
    public ResponseBean submitReport() throws CustomException {
        return ResponseBean.builder().msg("提交工作报告成功！").build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/uploadReport")
    @ApiOperation(value = "上传工作报告", notes = "上传工作报告接口")
    @ResponseBody
    public ResponseBean uploadReport() throws CustomException {
        return ResponseBean.builder().msg("上传工作报告成功！").build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/downloadReport")
    @ApiOperation(value = "下载工作报告", notes = "下载工作报告接口")
    @ResponseBody
    public ResponseBean downloadReport() throws CustomException {
        return ResponseBean.builder().msg("下载工作报告成功！").build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/initProjects")
    @ApiOperation(value = "项目信息初始化", notes = "项目信息初始化接口")
    @ResponseBody
    public ResponseBean initProjects() throws CustomException {
        return ResponseBean.builder().msg("项目信息初始化成功！").build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/modifyProjects")
    @ApiOperation(value = "项目信息修改", notes = "项目信息修改接口")
    @ResponseBody
    public ResponseBean modifyProjects() throws CustomException {
        return ResponseBean.builder().msg("项目信息修改成功！").build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/initInfo")
    @ApiOperation(value = "系统消息初始化", notes = "系统消息初始化接口")
    @ResponseBody
    public ResponseBean initInfo() throws CustomException {
        return ResponseBean.builder().msg("系统消息初始化成功！").build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/submitRequest")
    @ApiOperation(value = "事务申请提交", notes = "事务申请提交接口")
    @ResponseBody
    public ResponseBean submitRequest() throws CustomException {
        return ResponseBean.builder().msg("事务申请提交成功！").build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/initAttendance")
    @ApiOperation(value = "签到信息初始化", notes = "签到信息初始化接口")
    @ResponseBody
    public ResponseBean initAttendance() throws CustomException {
        return ResponseBean.builder().msg("签到信息初始化成功！").build().success();
    }

    @RequiresRoles(value = {"USER", "CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/markAttendance")
    @ApiOperation(value = "打卡签到", notes = "打卡签到接口")
    @ResponseBody
    public ResponseBean markAttendance() throws CustomException {
        return ResponseBean.builder().msg("打卡成功！").build().success();
    }
}
