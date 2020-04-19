package com.seasontemple.mproject.web.controller;

import cn.hutool.core.map.MapUtil;
import com.seasontemple.mproject.service.service.MenuService;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 菜单生成接口
 * @create: 2020/04/18 15:19:16
 */
@Api(value = "菜单生成接口", tags = "菜单生成接口控制层")
@RestController
public class MenuController extends BaseController{

    @Autowired
    private MenuService menuService;


    @GetMapping("/initMenu")
    @ResponseBody
    public ResponseBean initMenu() {
        return ResponseBean.builder().msg("生成菜单成功！").data(MapUtil.builder("menuList",menuService.initMenu()).build()).build().success();
    }
}
