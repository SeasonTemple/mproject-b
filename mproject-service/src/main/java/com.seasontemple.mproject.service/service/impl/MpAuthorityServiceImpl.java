package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpAuthorityMapper;
import com.seasontemple.mproject.dao.entity.MpAuthority;
import com.seasontemple.mproject.service.service.MpAuthorityService;
import org.springframework.stereotype.Service;

/**
 * 权限表(MpAuthority)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:04:25
 */
@Service("mpAuthorityService")
public class MpAuthorityServiceImpl extends ServiceImpl<MpAuthorityMapper, MpAuthority> implements MpAuthorityService {

}