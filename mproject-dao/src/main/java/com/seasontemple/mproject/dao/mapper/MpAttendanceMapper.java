package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.dto.SalaryDto;
import com.seasontemple.mproject.dao.entity.MpAttendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * (MpAttendance)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-05-22 18:05:59
 */
@Mapper
@Repository 
public interface MpAttendanceMapper extends BaseMapper<MpAttendance> {

    @Select("select user_detail.id as user_id, user_detail.real_name as real_name, user_detail.position as position , user_detail.salary as salary, sum(mp_attendance.time) as total from user_detail LEFT JOIN mp_attendance on user_detail.id = mp_attendance.user_id WHERE DATE_FORMAT(mp_attendance.day, '%Y%m' ) = DATE_FORMAT( CURDATE() , '%Y%m' ) GROUP BY mp_attendance.user_id")
    List<SalaryDto> getSalary();

}