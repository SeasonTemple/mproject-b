package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.dto.RoleAuth;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * (RoleAuth)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-04-18 18:27:29
 */
@Mapper
@Repository 
public interface RoleAuthMapper extends BaseMapper<RoleAuth> {

}