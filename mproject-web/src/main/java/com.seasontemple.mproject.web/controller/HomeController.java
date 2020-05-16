package com.seasontemple.mproject.web.controller;

import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.group.UserLoginValidatedGroup;
import com.seasontemple.mproject.dao.mapper.UserDetailMapper;
import com.seasontemple.mproject.service.service.HomeService;
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
 * @description: 主页接口控制层
 */
@Api(value = "主页接口", tags = "主页接口控制层")
@RestController
public class HomeController extends BaseController{

    @Autowired
    private HomeService homeService;

    @PostMapping(value = "/initUserData", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户详细信息", notes = "用户详细信息获取接口")
    @ResponseBody
    public ResponseBean initUserData(@Validated(value = {UserLoginValidatedGroup.class}) UserDetail userDetail, BindingResult bindingResult) throws CustomException {
        return ResponseBean.builder().data(homeService.initUserDetail(userDetail.getUserName())).build().success();
    }

    @PostMapping(value = "/initEcharts", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户信息可视化数据", notes = "用户信息可视化数据调用接口")
    @ResponseBody
    public ResponseBean initEcharts(@Validated(value = {UserLoginValidatedGroup.class}) UserDetail userDetail, BindingResult bindingResult) throws CustomException {

        return null;
    }
}
