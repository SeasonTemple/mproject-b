package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.entity.MpProject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * 项目组表(MpProject)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-05-04 00:50:09
 */
@Mapper
@Repository 
public interface MpProjectMapper extends BaseMapper<MpProject> {

}