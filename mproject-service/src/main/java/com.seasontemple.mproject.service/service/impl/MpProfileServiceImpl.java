package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpProfileMapper;
import com.seasontemple.mproject.dao.entity.MpProfile;
import com.seasontemple.mproject.service.service.MpProfileService;
import org.springframework.stereotype.Service;

/**
 * 用户详情表(MpProfile)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:04:25
 */
@Service("mpProfileService")
public class MpProfileServiceImpl extends ServiceImpl<MpProfileMapper, MpProfile> implements MpProfileService {

}