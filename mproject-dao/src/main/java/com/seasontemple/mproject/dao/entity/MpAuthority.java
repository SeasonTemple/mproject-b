package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 权限表(MpAuthority)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:21
 */
@SuppressWarnings("serial")
@Data
@ApiModel("权限表")
@Accessors(chain = true)
public class MpAuthority {

    private static final Log log = LogFactory.get();
    private static final long serialVersionUID = -62866012533837510L;
    /**
     * 权限ID
     */
    @ApiModelProperty("权限ID")
    private Integer id;
    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String authName;
    /**
     * 父级权限ID
     */
    @ApiModelProperty("父级权限ID")
    private Integer parentId;
    /**
     * 权限字段
     */
    @ApiModelProperty("权限字段")
    private String permission;
    /**
     * 权限对应的角色ID
     */
    @ApiModelProperty("权限对应的角色ID")
    private Integer roleId;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
}