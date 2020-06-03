package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.entity.MpInformation;
import com.seasontemple.mproject.dao.entity.MpRole;
import com.seasontemple.mproject.dao.mapper.MpInformationMapper;
import com.seasontemple.mproject.dao.mapper.MpRoleMapper;
import com.seasontemple.mproject.service.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description:
 */
@Service
@Transactional
public class SystemServiceImpl implements SystemService {

    @Autowired
    private MpRoleMapper mpRoleMapper;

    @Autowired
    private MpInformationMapper mpInformationMapper;

    @Override
    public Map initRoles() {
        return MapUtil.builder().put("roles", new LambdaQueryChainWrapper<>(mpRoleMapper).list()).build();
    }

    @Override
    public String modifyRole(MpRole role) {
        return mpRoleMapper.updateById(role) > 0 ? "角色更新成功！" : "角色更新失败！";
    }

    @Override
    public String addRole(MpRole role) {
        return mpRoleMapper.insert(role) > 0 ? "角色添加成功！" : "角色添加失败！";
    }

    @Override
    public String deleteRole(String ids) {
        String target = ids.replace("ids=", "");
        if (target.contains("&")) {
            List<String> idList = CollUtil.toList(ids.replace("ids=", "").split("&"));
            return mpRoleMapper.deleteBatchIds(idList) > 0 ? "角色删除成功！" : "角色删除失败！";
        } else {
            return mpRoleMapper.deleteById(target) > 0 ? "角色删除成功！" : "角色删除失败！";
        }
    }

    @Override
    public Map initInfo() {
        return MapUtil.builder().put("infoList", new LambdaQueryChainWrapper<>(mpInformationMapper).list()).build();
    }

    @Override
    public String modifyInfo(MpInformation information) {
        return mpInformationMapper.updateById(information) > 0 ? "消息更新成功！" : "消息更新失败！";

    }

    @Override
    public String addInfo(MpInformation info) {
        return mpInformationMapper.insert(info) > 0 ? "消息添加成功！" : "消息添加失败！";

    }

    @Override
    public String deleteInfo(String ids) {
        String target = ids.replace("ids=", "");
        if (target.contains("&")) {
            List<String> idList = CollUtil.toList(ids.replace("ids=", "").split("&"));
            return mpRoleMapper.deleteBatchIds(idList) > 0 ? "消息删除成功！" : "消息删除失败！";
        } else {
            return mpRoleMapper.deleteById(target) > 0 ? "消息删除成功！" : "消息删除失败！";
        }
    }
}
