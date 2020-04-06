package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import java.util.Date;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;


/**
 * 用户表(MpUser)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:1
 */
@SuppressWarnings("serial")
@Data
@ApiModel("用户表")
@Accessors(chain = true)
public class MpUser {

    private static final Log log = LogFactory.get();
    private static final long serialVersionUID = 516005918734603946L;

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
//    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;
    /**
     * 上次登录
     */
    @ApiModelProperty("上次登录")
//    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
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