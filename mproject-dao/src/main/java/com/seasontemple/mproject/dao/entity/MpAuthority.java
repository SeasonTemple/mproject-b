package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 权限表(MpAuthority)表实体类
 *
 * @author SeasonTemple
 * @since 2020-04-09 22:47:32
 */
@SuppressWarnings("serial")
@Data
@ApiModel("权限表")
@Accessors(chain = true)
public class MpAuthority {
    private static final long serialVersionUID = 589612715460554461L;
    /**
    * 权限ID
    */    
    @ApiModelProperty("权限ID")
    private Integer id;
    /**
    * 权限名称
    */    
    @ApiModelProperty("权限名称")
    private String authName;
    /**
    * 权限字段
    */    
    @ApiModelProperty("权限字段")
    private String permission;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }