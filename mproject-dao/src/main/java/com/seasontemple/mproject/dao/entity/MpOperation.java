package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 权限操作表(MpOperation)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:21
 */
@SuppressWarnings("serial")
@Data
@ApiModel("权限操作表")
@Accessors(chain = true)
public class MpOperation implements Serializable {
    private static final long serialVersionUID = 492043315021707840L;
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
    * 对应路由ID
    */    
    @ApiModelProperty("对应路由ID")
    private Integer routeId;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }