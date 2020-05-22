package com.seasontemple.mproject.dao.entity;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 签到日期表(MpAttendance)表实体类
 *
 * @author SeasonTemple
 * @since 2020-05-22 18:07:01
 */
@SuppressWarnings("serial")
@Data
@ApiModel("签到日期表")
@Accessors(chain = true)
public class MpAttendance {
    private static final long serialVersionUID = -46515199314306712L;
    /**
    * 签到日期表ID
    */    
    @ApiModelProperty("签到日期表ID")
    private Integer id;
    /**
    * 日签到次数
    */    
    @ApiModelProperty("日签到次数")
    private Integer time;
    /**
    * 今日日期
    */    
    @ApiModelProperty("今日日期")
    private Date day;
    /**
    * 本日第一次签到时间
    */    
    @ApiModelProperty("本日第一次签到时间")
    private Date first;
    /**
    * 本日第二次签到时间
    */    
    @ApiModelProperty("本日第二次签到时间")
    private Date second;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }