package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.seasontemple.mproject.dao.dto.UserDetail;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 项目组表(MpProject)表实体类
 *
 * @author SeasonTemple
 * @since 2020-05-04 00:50:07
 */
@SuppressWarnings("serial")
@Data
@ApiModel("项目表")
@Accessors(chain = true)
public class MpProject {
    private static final long serialVersionUID = 997276712216174563L;
    /**
    * 项目组ID
    */    
    @ApiModelProperty("项目组ID")
    private Integer id;
    /**
    * 项目组名称
    */    
    @ApiModelProperty("项目组名称")
    private String projectName;
    /**
    * 项目内容描述
    */    
    @ApiModelProperty("项目内容描述")
    private String description;
    /**
    * 项目进度
    */    
    @ApiModelProperty("项目进度")
    private Integer schedule;
    /**
    * 项目组负责人ID
    */    
    @ApiModelProperty("项目组负责人ID")
    private String leader;
    /**
    * 该组所属部门
    */    
    @ApiModelProperty("该组所属项目组")
    private Integer groupId;

    @ApiModelProperty("该组成员")
    @TableField(exist = false)
    private List<UserDetail> members;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }