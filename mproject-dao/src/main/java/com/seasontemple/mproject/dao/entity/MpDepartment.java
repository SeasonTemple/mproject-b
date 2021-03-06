package com.seasontemple.mproject.dao.entity;

import java.io.Serializable;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 部门表(MpDepartment)表实体类
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:43:21
 */
@SuppressWarnings("serial")
@Data
@ApiModel("部门表")
@Accessors(chain = true)
public class MpDepartment {

    private static final Log log = LogFactory.get();
    private static final long serialVersionUID = -58398175671472780L;
    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String depName;
    /**
     * 部长ID
     */
    @ApiModelProperty("部长ID")
    private String leader;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    protected Serializable pkVal() {
        return this.id;
    }
}