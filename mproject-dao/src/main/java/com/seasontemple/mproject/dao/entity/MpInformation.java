package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import java.util.Date;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统消息表(MpInformation)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:21
 */
@SuppressWarnings("serial")
@Data
@ApiModel("系统消息表")
@Accessors(chain = true)
public class MpInformation {

    private static final Log log = LogFactory.get();
    private static final long serialVersionUID = -12253639727598739L;
    /**
     * 系统消息ID
     */
    @ApiModelProperty("系统消息ID")
    private Integer id;
    /**
     * 消息标题
     */
    @ApiModelProperty("消息标题")
    private String title;
    /**
     * 消息内容
     */
    @ApiModelProperty("消息内容")
    private String content;
    /**
     * 消息类型
     */
    @ApiModelProperty("消息类型")
    private String type;
    /**
     * 信息发布时间
     */
    @ApiModelProperty("信息发布时间")
    private Date timeStamp;
    /**
     * 发布者ID
     */
    @ApiModelProperty("发布者ID")
    private String publisher;

    @ApiModelProperty("接收者用户名")
    private String receiver;
    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
}