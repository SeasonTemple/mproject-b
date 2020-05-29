package com.seasontemple.mproject.dao.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 筛选员工信息载体类
 */
@Data
@ApiModel("筛选员工信息载体类")
public class StaffSearchDto {
    /**
     * 所属部门
     */
    private String depName;
    /**
     * 所属项目组
     */
    private String groupName;
    /**
     * 所参与项目
     */
    private String projectName;
    /**
     * 个人信息
     */
    private String userDetail;

}
