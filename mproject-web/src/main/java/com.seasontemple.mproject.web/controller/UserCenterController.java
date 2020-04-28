package com.seasontemple.mproject.web.controller;

import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.utils.custom.ResponseBean;
import io.swagger.annotations.Api;
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

    public ResponseBean getUserDetail(UserRole userRole) {

        return null;
    }

}
