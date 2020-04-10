package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 权限操作表(MpOperation)表实体类
 *
 * @author SeasonTemple
 * @since 2020-04-09 22:47:32
 */
@SuppressWarnings("serial")
@Data
@ApiModel("权限操作表")
@Accessors(chain = true)
public class MpOperation {
    private static final long serialVersionUID = 329053557843040053L;
    /**
    * 权限操作映射ID
    */    
    @ApiModelProperty("权限操作映射ID")
    private Integer id;
    /**
    * 操作名称
    */    
    @ApiModelProperty("操作名称")
    private String operationName;
    /**
    * 对应权限ID
    */    
    @ApiModelProperty("对应权限ID")
    private Integer authId;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }