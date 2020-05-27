package com.seasontemple.mproject.dao.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 工作日志表(MpReport)表实体类
 *
 * @author SeasonTemple
 * @since 2020-05-27 20:02:10
 */
@SuppressWarnings("serial")
@Data
@ApiModel("工作日志表")
@Accessors(chain = true)
public class MpReport {
    private static final long serialVersionUID = -27454701290759128L;
    /**
    * 日志ID
    */    
    @ApiModelProperty("日志ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 日志标题
    */    
    @ApiModelProperty("日志标题")
    private String title;
    /**
    * 日志内容
    */    
    @ApiModelProperty("日志内容")
    private String content;
    /**
    * 发布日期
    */    
    @ApiModelProperty("发布日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publish;
    /**
    * 发布者
    */    
    @ApiModelProperty("发布者")
    private String owner;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }