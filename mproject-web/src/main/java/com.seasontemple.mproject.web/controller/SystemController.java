package com.seasontemple.mproject.web.controller;

import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 系统控制接口控制层
 */
@Api(value = "系统控制接口", tags = "系统控制接口控制层")
@RestController
public class SystemController extends BaseController {


    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/initRoles", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "角色列表初始化初始化", notes = "角色列表初始化初始化API")
    @ResponseBody
    public ResponseBean initRoles() throws CustomException {
        return ResponseBean.builder().msg("角色列表初始化初始化成功！").build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/modifyRole", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "角色信息修改", notes = "角色信息修改API")
    @ResponseBody
    public ResponseBean modifyRole() throws CustomException {
        return ResponseBean.builder().msg("角色信息修改成功！").build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/addRole", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "角色添加", notes = "角色添加API")
    @ResponseBody
    public ResponseBean addRole() throws CustomException {
        return ResponseBean.builder().msg("角色添加成功！").build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/deleteRole", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "角色删除", notes = "角色删除API")
    @ResponseBody
    public ResponseBean deleteRole() throws CustomException {
        return ResponseBean.builder().msg("角色删除成功！").build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/initInfo", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "消息初始化", notes = "消息初始化API")
    @ResponseBody
    public ResponseBean initInfo() throws CustomException {
        return ResponseBean.builder().msg("消息初始化成功！").build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/modifyInfo", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "消息修改", notes = "消息修改API")
    @ResponseBody
    public ResponseBean modifyInfo() throws CustomException {
        return ResponseBean.builder().msg("消息修改成功！").build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/addInfo", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "消息添加", notes = "消息添加API")
    @ResponseBody
    public ResponseBean addInfo() throws CustomException {
        return ResponseBean.builder().msg("消息添加成功！").build().success();
    }

    @RequiresRoles(value = {"CUSTOM", "ADMIN"}, logical = Logical.OR)
    @PostMapping(value = "/deleteInfo", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "消息删除", notes = "消息删除API")
    @ResponseBody
    public ResponseBean deleteInfo() throws CustomException {
        return ResponseBean.builder().msg("消息删除成功！").build().success();
    }
}
