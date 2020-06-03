package com.seasontemple.mproject.dao.dto;

import com.seasontemple.mproject.dao.entity.MpAttendance;
import com.seasontemple.mproject.dao.entity.MpUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 薪水相关数据载体类
 */
@Data
@ApiModel("薪水相关数据载体类")
public class SalaryDto {

    private String userId;

    private String realName;

    private String position;

    private String salary;

    private Integer total;

}
