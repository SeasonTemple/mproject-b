package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色权限对应表(MpRoleAuth)表实体类
 *
 * @author SeasonTemple
 * @since 2020-04-18 17:09:27
 */
@SuppressWarnings("serial")
@Data
@ApiModel("角色权限对应表")
@Accessors(chain = true)
public class MpRoleAuth {
    private static final long serialVersionUID = 421993626970588332L;
    /**
    * 角色权限对应ID
    */    
    @ApiModelProperty("角色权限对应ID")
    private Integer id;
    /**
    * 角色ID
    */    
    @ApiModelProperty("角色ID")
    private Integer roleId;
    /**
    * 权限ID
    */    
    @ApiModelProperty("权限ID")
    private Integer authId;

    /**
     * 获取主键值
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }