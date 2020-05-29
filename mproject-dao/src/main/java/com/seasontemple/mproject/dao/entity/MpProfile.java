package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户详情表(MpProfile)表实体类
 *
 * @author SeasonTemple
 * @since 2020-05-14 00:07:07
 */
@SuppressWarnings("serial")
@Data
@ApiModel("用户详情表")
@Accessors(chain = true)
public class MpProfile {
    private static final long serialVersionUID = 297697347530647112L;
    /**
    * 用户详情ID
    */    
    @ApiModelProperty("用户详情ID")
    private Integer id;
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
    * 性别
    */    
    @ApiModelProperty("性别")
    private Integer sex;
    /**
    * 职位
    */    
    @ApiModelProperty("职位")
    private String position;
    /**
    * 用户头像路由ID
    */    
    @ApiModelProperty("用户头像路由")
    private String avatarUrl;
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
    * 年龄
    */    
    @ApiModelProperty("年龄")
    private Integer age;
    /**
    * 邮箱地址
    */    
    @ApiModelProperty("邮箱地址")
    private String email;
    /**
    * 籍贯
    */    
    @ApiModelProperty("籍贯")
    private String origin;
    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }