package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色表(MpRole)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:21
 */
@SuppressWarnings("serial")
@Data
@ApiModel("角色表")
@Accessors(chain = true)
public class MpRole implements Serializable {

    private static final Log log = LogFactory.get();
    private static final long serialVersionUID = -91195019613365320L;
    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Integer id;
    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    private String roleName;
    /**
     * 角色状态
     */
    @ApiModelProperty("角色状态")
    private Integer state;
    /**
     * 角色有效时间
     */
    @ApiModelProperty("角色有效时间")
    private Integer limitTime;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
}