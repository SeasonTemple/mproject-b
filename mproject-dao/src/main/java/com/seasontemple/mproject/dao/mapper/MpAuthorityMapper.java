package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.entity.MpAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * 权限表(MpAuthority)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-03-26 18:32:40
 */
@Mapper
@Repository 
public interface MpAuthorityMapper extends BaseMapper<MpAuthority> {

}