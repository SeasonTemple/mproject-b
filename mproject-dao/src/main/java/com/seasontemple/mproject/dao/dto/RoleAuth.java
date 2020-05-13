package com.seasontemple.mproject.dao.dto;

import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (RoleAuth)表实体类
 *
 * @author SeasonTemple
 * @since 2020-04-18 18:27:29
 */
@SuppressWarnings("serial")
@Data
@ApiModel("$tableInfo.comment")
@Accessors(chain = true)
public class RoleAuth {
    private static final long serialVersionUID = 370048067320273771L;
    /**
    * 角色ID
    */    
    @ApiModelProperty("角色ID")
    private Integer id;
    /**
    * 角色名
    */    
    @ApiModelProperty("角色名")
    private String name;
    /**
    * 角色状态
    */    
    @ApiModelProperty("角色状态")
    private Integer state;
    /**
    * 角色有效时间(min)
    */    
    @ApiModelProperty("角色有效时间(min)")
    private Integer limitTime;
    /**
    * 权限名称
    */    
    @ApiModelProperty("权限名称")
    private String auth;
    /**
    * 权限字段
    */    
    @ApiModelProperty("权限字段")
    private String permission;

}