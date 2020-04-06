package com.seasontemple.mproject.dao.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (UserRole)表实体类
 *
 * @author SeasonTemple
 * @since 2020-04-07 00:33:58
 */
@SuppressWarnings("serial")
@Data
@ApiModel("userRole")
@Accessors(chain = true)
public class UserRole {
    private static final long serialVersionUID = 947836508229595709L;
    /**
    * 用户ID
    */    
    @ApiModelProperty("用户ID")
    @TableId
    private String id;
    /**
    * 用户名
    */    
    @ApiModelProperty("用户名")
    private String userName;
    /**
    * 加密密码
    */    
    @ApiModelProperty("加密密码")
    private String passWord;
    /**
    * 加密盐
    */    
    @ApiModelProperty("加密盐")
    private String salt;
    /**
    * 用户token
    */    
    @ApiModelProperty("用户token")
    private String token;
    /**
    * 账户状态
    */    
    @ApiModelProperty("账户状态")
    private Integer accountStatus;
    /**
    * 角色ID
    */    
    @ApiModelProperty("角色ID")
    private Integer roleId;
    /**
    * 角色名
    */    
    @ApiModelProperty("角色名")
    private String roleName;
    /**
    * 角色状态
    */    
    @ApiModelProperty("角色状态")
    private Integer roleState;
    /**
    * 角色有效时间
    */    
    @ApiModelProperty("角色有效时间")
    private Integer roleLimit;

}