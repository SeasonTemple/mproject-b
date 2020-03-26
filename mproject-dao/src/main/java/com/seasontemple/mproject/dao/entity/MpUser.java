package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户表(MpUser)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:21
 */
@SuppressWarnings("serial")
@Data
@ApiModel("用户表")
@Accessors(chain = true)
public class MpUser implements Serializable {
    private static final long serialVersionUID = 516005918734603946L;
    /**
    * 用户ID
    */    
    @ApiModelProperty("用户ID")
    private String id;
    /**
    * 用户名
    */    
    @ApiModelProperty("用户名")
    private String userName;
    /**
    * 加密密码
    */    
    @ApiModelProperty("加密密码")
    private String passWord;
    /**
    * 加密盐
    */    
    @ApiModelProperty("加密盐")
    private String salt;
    /**
    * 用户token
    */    
    @ApiModelProperty("用户token")
    private String token;
    /**
    * 当前角色ID
    */    
    @ApiModelProperty("当前角色ID")
    private Integer roleId;
    /**
    * 账号创建时间
    */    
    @ApiModelProperty("账号创建时间")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;
    /**
    * 上次登录
    */    
    @ApiModelProperty("上次登录")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date lastLogin;
    /**
    * 账户状态
    */    
    @ApiModelProperty("账户状态")
    private Integer status;
    /**
    * 用户详情ID
    */    
    @ApiModelProperty("用户详情ID")
    private String profileId;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }