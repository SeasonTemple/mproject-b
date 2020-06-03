package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色表(MpRole)表实体类
 *
 * @author SeasonTemple
 * @since 2020-04-09 22:47:32
 */
@SuppressWarnings("serial")
@Data
@ApiModel("角色表")
@Accessors(chain = true)
public class MpRole {
    private static final long serialVersionUID = -67701194894848067L;
    /**
    * 角色ID
    */    
    @ApiModelProperty("角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 角色名
    */    
    @ApiModelProperty("角色名")
    private String roleName;
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
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }