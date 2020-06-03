package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 权限表(MpAuthority)表实体类
 * @author SeasonTemple
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
    @TableId(value = "id", type = IdType.AUTO)
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