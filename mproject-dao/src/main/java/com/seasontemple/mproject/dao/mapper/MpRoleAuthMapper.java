package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.entity.MpRoleAuth;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * 角色权限对应表(MpRoleAuth)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-04-18 17:08:18
 */
@Mapper
@Repository 
public interface MpRoleAuthMapper extends BaseMapper<MpRoleAuth> {

}