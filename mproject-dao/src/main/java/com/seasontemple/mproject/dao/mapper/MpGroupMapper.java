package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.entity.MpGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * (MpGroup)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-05-22 18:03:52
 */
@Mapper
@Repository 
public interface MpGroupMapper extends BaseMapper<MpGroup> {

}