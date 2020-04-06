package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 路由表(MpRoute)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:21
 */
@SuppressWarnings("serial")
@Data
@ApiModel("路由表")
@Accessors(chain = true)
public class MpRoute {

    private static final Log log = LogFactory.get();
    private static final long serialVersionUID = -75583034344336586L;
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
     * 路由类型
     */
    @ApiModelProperty("路由类型")
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