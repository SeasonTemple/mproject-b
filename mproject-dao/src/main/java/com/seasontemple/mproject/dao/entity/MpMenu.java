package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单表(MpMenu)表实体类
 *
 * @author SeasonTemple
 * @since 2020-04-09 22:47:32
 */
@SuppressWarnings("serial")
@Data
@ApiModel("菜单表")
@Accessors(chain = true)
public class MpMenu {
    private static final long serialVersionUID = 970261207914031835L;
    /**
    * 菜单ID
    */    
    @ApiModelProperty("菜单ID")
    private Integer id;
    /**
    * 菜单名称
    */    
    @ApiModelProperty("菜单名称")
    private String menuName;
    /**
    * 可访问权限字段
    */    
    @ApiModelProperty("可访问权限字段")
    private String permission;
    /**
    * 菜单路由ID
    */    
    @ApiModelProperty("菜单路由名")
    private String url;
    /**
    * 父级菜单ID
    */    
    @ApiModelProperty("父级菜单ID")
    private Integer parentId;
    /**
    * 菜单图标
    */    
    @ApiModelProperty("菜单状态")
    private String status;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }