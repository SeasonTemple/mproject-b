package com.seasontemple.mproject.dao.dto;

import com.seasontemple.mproject.dao.entity.MpReport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 接受所需下载的日志列表载体类
 */
@Data
@ApiModel("日志列表载体类")
public class DownloadReportDto {
    @ApiModelProperty("所需下载日志列表")
    String file;
}
