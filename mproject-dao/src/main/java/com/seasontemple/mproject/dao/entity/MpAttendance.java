package com.seasontemple.mproject.dao.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

/**
 * 签到日期表(MpAttendance)表实体类
 *
 * @author SeasonTemple
 * @since 2020-05-23 16:04:10
 */
@SuppressWarnings("serial")
@Data
@ApiModel("签到日期表")
@Accessors(chain = true)
public class MpAttendance {
    private static final long serialVersionUID = -81489034464274625L;
    /**
     * 签到日期表ID
     */
    @ApiModelProperty("签到日期表ID")
    @TableId(value = "id", type = IdType.AUTO)
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date day;
    /**
     * 本日第一次签到时间
     */
    @ApiModelProperty("本日第一次签到时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date first;
    /**
     * 本日第二次签到时间
     */
    @ApiModelProperty("本日第二次签到时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date second;
    /**
     * 签到用户
     */
    @ApiModelProperty("签到用户")
    private String userId;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
}