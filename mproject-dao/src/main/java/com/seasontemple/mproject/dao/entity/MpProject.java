package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 项目组表(MpProject)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 22:09:02
 */
@SuppressWarnings("serial")
@Data
@ApiModel("项目组表")
@Accessors(chain = true)
public class MpProject {

    private static final Log log = LogFactory.get();
    private static final long serialVersionUID = -49634630090241933L;
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
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
}