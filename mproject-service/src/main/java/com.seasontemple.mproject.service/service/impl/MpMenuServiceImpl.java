package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpMenuMapper;
import com.seasontemple.mproject.dao.entity.MpMenu;
import com.seasontemple.mproject.service.service.MpMenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单表(MpMenu)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:04:25
 */
@Service("mpMenuService")
public class MpMenuServiceImpl extends ServiceImpl<MpMenuMapper, MpMenu> implements MpMenuService {

}