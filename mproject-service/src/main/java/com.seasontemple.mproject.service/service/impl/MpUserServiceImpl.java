package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.service.service.MpUserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(MpUser)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-04-10 23:38:22
 */
@Service("mpUserService")
public class MpUserServiceImpl extends ServiceImpl<MpUserMapper, MpUser> implements MpUserService {

}