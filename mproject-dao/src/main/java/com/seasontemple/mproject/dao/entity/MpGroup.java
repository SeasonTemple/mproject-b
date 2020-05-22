package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * (MpGroup)表实体类
 *
 * @author SeasonTemple
 * @since 2020-05-22 18:03:50
 */
@SuppressWarnings("serial")
@Data
@ApiModel("$tableInfo.comment")
@Accessors(chain = true)
public class MpGroup {
    private static final long serialVersionUID = -75759834504314594L;
    /**
    * 项目组（团队）ID
    */    
    @ApiModelProperty("项目组（团队）ID")
    private Integer id;
    /**
    * 项目组名称
    */    
    @ApiModelProperty("项目组名称")
    private String groupName;
    /**
    * 组长ID
    */    
    @ApiModelProperty("组长ID")
    private String leader;
    /**
    * 所属部门ID
    */    
    @ApiModelProperty("所属部门ID")
    private Integer depId;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
    }