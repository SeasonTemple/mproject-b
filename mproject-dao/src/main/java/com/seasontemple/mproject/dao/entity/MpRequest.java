package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import java.util.Date;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 申请表(MpRequest)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:21
 */
@SuppressWarnings("serial")
@Data
@ApiModel("申请表")
@Accessors(chain = true)
public class MpRequest {

    private static final Log log = LogFactory.get();
    private static final long serialVersionUID = -96452007069948536L;
    /**
     * 申请ID
     */
    @ApiModelProperty("申请ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 申请人
     */
    @ApiModelProperty("申请人")
    private String applicant;
    /**
     * 审核人
     */
    @ApiModelProperty("审核人")
    private String auditor;
    /**
     * 申请类型
     */
    @ApiModelProperty("申请类型")
    private Integer type;
    /**
     * 其他类型补充
     */
    @ApiModelProperty("申请日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date requestTime;
    /**
     * 请假开始日期
     */
    @ApiModelProperty("请假开始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 请假结束日期
     */
    @ApiModelProperty("请假结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 申请理由
     */
    @ApiModelProperty("申请理由")
    private String reason;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
}