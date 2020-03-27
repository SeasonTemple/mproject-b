package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpProjectMapper;
import com.seasontemple.mproject.dao.entity.MpProject;
import com.seasontemple.mproject.service.service.MpProjectService;
import org.springframework.stereotype.Service;

/**
 * 项目组表(MpProject)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:04:25
 */
@Service("mpProjectService")
public class MpProjectServiceImpl extends ServiceImpl<MpProjectMapper, MpProject> implements MpProjectService {

}