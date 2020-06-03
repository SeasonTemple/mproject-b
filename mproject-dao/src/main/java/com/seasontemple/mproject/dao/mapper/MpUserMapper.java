package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.entity.MpUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表(MpUser)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-03-26 18:32:40
 */
@Mapper
@Repository
public interface MpUserMapper extends BaseMapper<MpUser> {

    //    @Select("select `user`.`id` AS `id`,`user`.`user_name` AS `user_name`,`user`.`pass_word` AS `pass_word`,`user`.`create_time` AS `create_time`,`user`.`last_login` AS `last_login`,`profile`.`real_name` AS `real_name`,`profile`.`phone` AS `phone`,`profile`.`sex` AS `sex`,`profile`.`position` AS `position`,`profile`.`id_number` AS `id_number`,`profile`.`group_id` AS `group_id`,`profile`.`dep_id` AS `dep_id`,`profile`.`leader_id` AS `leader`,`profile`.`salary` AS `salary`,`profile`.`attendance` AS `attendance`,`profile`.`age` AS `age`,`profile`.`email` AS `email`,`profile`.`from` AS `from`,`route`.`url` AS `avatar_url` from ((`mp_user` `user` left join `mp_profile` `profile` on((`user`.`profile_id` = `profile`.`id`))) left join `mp_route` `route` on((`profile`.`avatar_id` = `route`.`id`))) where `user`.`user_name` = #{userName} group by `user`.`id`")
//    UserDetail getUserDetail(@Param("userName") String userName);
    @Select("select id, user_name from mproject.mp_user where role_id = #{role_id}")
    List<MpUser> selectByRole(@Param("role_id") Integer roleId);

    @Select("select * from mproject.mp_user")
    List<MpUser> selectAll();


}