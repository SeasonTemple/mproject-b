package com.seasontemple.mproject.dao.dto;

import com.seasontemple.mproject.dao.entity.MpDepartment;
import com.seasontemple.mproject.dao.entity.MpProject;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 职能信息载体
 */
@Data
@ApiModel("depProDto")
@Accessors(chain = true)
public class DepProDto {

    private List<MpDepartment> departments;

    private List<MpProject> projects;
}
