package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 路由表(MpRoute)表实体类
 *
 * @author SeasonTemple
 * @since 2020-04-09 22:47:32
 */
@SuppressWarnings("serial")
@Data
@ApiModel("路由表")
@Accessors(chain = true)
public class MpRoute {
    private static final long serialVersionUID = -73295926238462817L;
    /**
    * 路由ID
    */    
    @ApiModelProperty("路由ID")
    private Integer id;
    /**
    * 路由名称
    */    
    @ApiModelProperty("路由名称")
    private String routeName;
    /**
    * 路由描述
    */    
    @ApiModelProperty("路由描述")
    private String description;
    /**
    * 路由类型(0指代菜单功能路由地址,1指代图片等静态资源路由地址,2指代权限路由地址,此后按需添加即可)
    */    
    @ApiModelProperty("路由类型(0指代菜单功能路由地址,1指代图片等静态资源路由地址,2指代权限路由地址,此后按需添加即可)")
    private Integer type;
    /**
    * 路由地址
    */    
    @ApiModelProperty("路由地址")
    private String url;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }