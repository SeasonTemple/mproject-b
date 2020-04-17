package com.seasontemple.mproject.dao.dto;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 用户登录所需信息载体
 * @create: 2020/04/05 01:41:45
 */
@Data
@ApiModel("loginDto")
@Accessors(chain = true)
public class LoginDto{

    private static Log log = Log.get();

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String passWord;

    @ApiModelProperty(value = "权限字段",hidden = true)
    private String permission;

    @ApiModelProperty(value = "角色名",hidden = true)
    private String roleName;

    @ApiModelProperty(value = "加密盐",hidden = true)
    private byte[] salt;

    @ApiModelProperty(value = "令牌",hidden = true)
    private String token;

    @ApiModelProperty(value = "登录时间",hidden = true)
    private Date loginTime;

    @ApiModelProperty(value = "退出时间",hidden = true)
    private Date logoutTime;
}
