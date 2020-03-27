package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seasontemple.mproject.dao.mapper.MpDepartmentMapper;
import com.seasontemple.mproject.dao.entity.MpDepartment;
import com.seasontemple.mproject.service.service.MpDepartmentService;
import org.springframework.stereotype.Service;

/**
 * 部门表(MpDepartment)表服务实现类
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:04:25
 */
@Service("mpDepartmentService")
public class MpDepartmentServiceImpl extends ServiceImpl<MpDepartmentMapper, MpDepartment> implements MpDepartmentService {

}