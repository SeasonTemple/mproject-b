package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.dto.UserMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * (UserMenu)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-04-18 18:45:28
 */
@Mapper
@Repository 
public interface UserMenuMapper extends BaseMapper<UserMenu> {

}