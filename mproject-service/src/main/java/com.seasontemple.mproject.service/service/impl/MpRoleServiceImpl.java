package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpRoleMapper;
import com.seasontemple.mproject.dao.entity.MpRole;
import com.seasontemple.mproject.service.service.MpRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表(MpRole)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:04:25
 */
@Service("mpRoleService")
public class MpRoleServiceImpl extends ServiceImpl<MpRoleMapper, MpRole> implements MpRoleService {

}