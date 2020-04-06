package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.dto.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * (UserRole)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-04-07 00:33:58
 */
@Mapper
@Repository 
public interface UserRoleMapper extends BaseMapper<UserRole> {

}