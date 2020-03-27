package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpOperationMapper;
import com.seasontemple.mproject.dao.entity.MpOperation;
import com.seasontemple.mproject.service.service.MpOperationService;
import org.springframework.stereotype.Service;

/**
 * 权限操作表(MpOperation)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:04:25
 */
@Service("mpOperationService")
public class MpOperationServiceImpl extends ServiceImpl<MpOperationMapper, MpOperation> implements MpOperationService {

}