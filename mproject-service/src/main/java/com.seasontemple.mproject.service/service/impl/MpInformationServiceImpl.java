package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpInformationMapper;
import com.seasontemple.mproject.dao.entity.MpInformation;
import com.seasontemple.mproject.service.service.MpInformationService;
import org.springframework.stereotype.Service;

/**
 * 系统消息表(MpInformation)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:04:25
 */
@Service("mpInformationService")
public class MpInformationServiceImpl extends ServiceImpl<MpInformationMapper, MpInformation> implements MpInformationService {

}