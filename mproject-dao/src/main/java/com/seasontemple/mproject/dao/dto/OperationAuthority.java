package com.seasontemple.mproject.dao.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (OperationAuthority)表实体类
 * @description: 获取用户对应权限信息
 * @author SeasonTemple
 * @since 2020-04-10 01:01:27
 */
@SuppressWarnings("serial")
@Data
@ApiModel("operationAuthority")
@Accessors(chain = true)
public class OperationAuthority {
    private static final long serialVersionUID = -73533583255801287L;
    /**
    * 操作名称
    */    
    @ApiModelProperty("操作名称")
    private String operationName;
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
    * 权限ID
    */    
    @ApiModelProperty("权限ID")
    private Integer aId;
    /**
    * 权限操作映射ID
    */    
    @ApiModelProperty("权限操作映射ID")
    @TableId(type = IdType.AUTO)
    private Integer oId;

}