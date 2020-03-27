package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpRouteMapper;
import com.seasontemple.mproject.dao.entity.MpRoute;
import com.seasontemple.mproject.service.service.MpRouteService;
import org.springframework.stereotype.Service;

/**
 * 路由表(MpRoute)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:04:25
 */
@Service("mpRouteService")
public class MpRouteServiceImpl extends ServiceImpl<MpRouteMapper, MpRoute> implements MpRouteService {

}