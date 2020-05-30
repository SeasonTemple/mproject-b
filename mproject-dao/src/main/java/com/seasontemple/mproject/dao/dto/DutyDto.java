package com.seasontemple.mproject.dao.dto;

import com.seasontemple.mproject.dao.entity.MpDepartment;
import com.seasontemple.mproject.dao.entity.MpGroup;
import com.seasontemple.mproject.dao.entity.MpProject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 职能信息载体类
 */
@Data
@ApiModel("职能信息载体类")
public class DutyDto {

    private MpDepartment department;

    private MpGroup group;

    private MpProject project;
}
