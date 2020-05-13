package com.seasontemple.mproject.dao.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.seasontemple.mproject.dao.group.UserLoginValidatedGroup;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

/**
 * 用户表(MpUser)表实体类
 *
 * @author SeasonTemple
 * @since 2020-05-12 20:58:03
 */
@SuppressWarnings("serial")
@Data
@ApiModel("用户表")
@Accessors(chain = true)
public class MpUser {
    private static final long serialVersionUID = -72909022654325750L;
    /**
    * 用户ID
    */    
    @ApiModelProperty("用户ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
    * 用户名
    */    
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空", groups = {UserLoginValidatedGroup.class})
    @Length(min = 4, max = 20, message = "用户名应介于4到20个字符，其中1个汉字记为2字符", groups = {UserLoginValidatedGroup.class})
    private String userName;
    /**
    * 加密密码
    */    
    @ApiModelProperty("加密密码")
    @NotBlank(message = "密码不能为空", groups = {UserLoginValidatedGroup.class})
    @Length(min = 8, max = 20, message = "密码应介于8到20个字符，其中1个汉字记为2字符", groups = {UserLoginValidatedGroup.class})
    private String passWord;
    /**
    * 加密盐
    */    
    @ApiModelProperty("加密盐")
    private byte[] salt;
    /**
    * 用户token
    */    
    @ApiModelProperty("用户token")
    private String token;
    /**
    * 当前角色ID
    */    
    @ApiModelProperty("当前角色ID")
    @TableField(fill = FieldFill.INSERT)
    private Integer roleId;
    /**
    * 账号创建时间
    */    
    @ApiModelProperty("账号创建时间")
//    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
    * 上次登录
    */    
    @ApiModelProperty("上次登录")
//    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @TableField(value = "last_login", fill = FieldFill.INSERT_UPDATE)
    private Date lastLogin;
    /**
    * 账户状态
    */    
    @ApiModelProperty("账户状态")
    @TableField(fill = FieldFill.INSERT)
    private Integer status;
    /**
    * 详情ID
    */    
    @ApiModelProperty("详情ID")
    private Integer profileId;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }