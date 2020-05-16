package com.seasontemple.mproject.web.controller;

import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.group.UserLoginValidatedGroup;
import com.seasontemple.mproject.service.service.ProfileService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 个人中心接口控制层
 */
@Api(value = "个人中心接口", tags = "个人中心接口控制层")
@RestController
public class ProfileController extends BaseController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserCenterService userCenterService;

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
}
