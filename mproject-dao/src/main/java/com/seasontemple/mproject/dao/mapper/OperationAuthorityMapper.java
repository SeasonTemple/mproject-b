package com.seasontemple.mproject.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seasontemple.mproject.dao.dto.OperationAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * (OperationAuthority)表数据库访问层
 *
 * @author SeasonTemple
 * @since 2020-04-10 01:07:04
 */
@Mapper
@Repository 
public interface OperationAuthorityMapper extends BaseMapper<OperationAuthority> {

}