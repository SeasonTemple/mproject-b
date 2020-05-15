package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.dto.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * (UserDetail)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-05-15 21:32:28
 */
@Mapper
@Repository 
public interface UserDetailMapper extends BaseMapper<UserDetail> {

}