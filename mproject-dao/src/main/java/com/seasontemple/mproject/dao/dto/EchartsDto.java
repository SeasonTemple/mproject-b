package com.seasontemple.mproject.dao.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * @author Season Temple
 * @program: mproject
 * @description: 数据可视化载体
 */
@Data
@ApiModel("数据可视化载体")
public class EchartsDto {

    private List<Map<String,String>> projectData;
    private List<Map<String,String>> ageData;
    private List<Map<String,Integer>> attendanceData;

}
