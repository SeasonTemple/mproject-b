package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.entity.MpAttendance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * (MpAttendance)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-05-22 18:05:59
 */
@Mapper
@Repository 
public interface MpAttendanceMapper extends BaseMapper<MpAttendance> {

}