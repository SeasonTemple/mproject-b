package com.seasontemple.mproject.web.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.service.service.MpUserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表(MpUser)表控制层
 *
 * @author SeasonTemple
 * @since 2020-04-10 23:36:30
 */
@RestController
@RequestMapping("mpUser")
public class MpUserController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MpUserService mpUserService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param mpUser 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<MpUser> page, MpUser mpUser) {
        return success(this.mpUserService.page(page, new QueryWrapper<>(mpUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
//    @RequiresRoles("USER")
    @RequiresAuthentication
    public R selectOne(@PathVariable Serializable id) {
        return success(this.mpUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mpUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody MpUser mpUser) {
        return success(this.mpUserService.save(mpUser));
    }

    /**
     * 修改数据
     *
     * @param mpUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody MpUser mpUser) {
        return success(this.mpUserService.updateById(mpUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.mpUserService.removeByIds(idList));
    }
}