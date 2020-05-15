package com.seasontemple.mproject.web.controller;

import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.group.UserLoginValidatedGroup;
import com.seasontemple.mproject.dao.mapper.UserDetailMapper;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 个人中心接口控制层
 */
@Api(value = "个人中心接口", tags = "个人中心接口控制层")
@RestController
public class ProfileController {

    @Autowired
    private UserDetailMapper userDetailMapper;

    @PostMapping(value = "/modifyDetail", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户详细信息初始化", notes = "用户详细信息初始化获取接口")
    @ResponseBody
    private ResponseBean modifyDetail(@Validated(value = {UserLoginValidatedGroup.class}) UserDetail userDetail, BindingResult bindingResult) throws CustomException {

        return null;
    }
}
