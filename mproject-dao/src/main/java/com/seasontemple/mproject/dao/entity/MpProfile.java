package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import java.util.Date;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户详情表(MpProfile)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:21
 */
@SuppressWarnings("serial")
@Data
@ApiModel("用户详情表")
@Accessors(chain = true)
public class MpProfile implements Serializable {

    private static final Log log = LogFactory.get();
    private static final long serialVersionUID = 980249560477942643L;
    /**
     * 用户详情ID
     */
    @ApiModelProperty("用户详情ID")
    private String id;
    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;
    /**
     * 鎬у埆
     */
    @ApiModelProperty("鎬у埆")
    private Integer sex;
    /**
     * 职位
     */
    @ApiModelProperty("职位")
    private String position;
    /**
     * 用户头像路由ID
     */
    @ApiModelProperty("用户头像路由ID")
    private Integer avatarId;
    /**
     * 身份证号码
     */
    @ApiModelProperty("身份证号码")
    private String idNumber;
    /**
     * 所在项目组ID
     */
    @ApiModelProperty("所在项目组ID")
    private Integer groupId;
    /**
     * 所在部门ID
     */
    @ApiModelProperty("所在部门ID")
    private Integer depId;
    /**
     * 直属领导ID
     */
    @ApiModelProperty("直属领导ID")
    private String leaderId;
    /**
     * 薪水
     */
    @ApiModelProperty("薪水")
    private Double salary;
    /**
     * 工号
     */
    @ApiModelProperty("工号")
    private String employeeNumber;
    /**
     * 入职时间
     */
    @ApiModelProperty("入职时间")
    private Date entryDate;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
}