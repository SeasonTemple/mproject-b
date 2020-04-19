package com.seasontemple.mproject.dao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 系统菜单生成实体
 * @create: 2020/04/18 15:43:44
 */
@Data
@ApiModel("userMenuDto")
@Accessors(chain = true)
public class UserMenuDto {

    /**
     * 菜单ID
     */
    @ApiModelProperty("完整菜单ID")
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
    @ApiModelProperty("菜单路由ID")
    private Integer routeId;
    /**
     * 父级菜单ID
     */
    @ApiModelProperty("父级菜单ID")
    private Integer parentId;
    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String menuIcon;
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
   /* @ApiModelProperty("路由类型(0指代菜单功能路由地址,1指代图片等静态资源路由地址,2指代权限路由地址,此后按需添加即可)")
    private Integer type;*/
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
