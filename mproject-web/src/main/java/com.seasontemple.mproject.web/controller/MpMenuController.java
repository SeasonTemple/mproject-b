package com.seasontemple.mproject.web.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seasontemple.mproject.dao.entity.MpMenu;
import com.seasontemple.mproject.web.service.MpMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单表(MpMenu)表控制层
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:47:46
 */
@RestController
@RequestMapping("mpMenu")
public class MpMenuController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MpMenuService mpMenuService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param mpMenu 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<MpMenu> page, MpMenu mpMenu) {
        return success(this.mpMenuService.page(page, new QueryWrapper<>(mpMenu)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.mpMenuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mpMenu 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody MpMenu mpMenu) {
        return success(this.mpMenuService.save(mpMenu));
    }

    /**
     * 修改数据
     *
     * @param mpMenu 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody MpMenu mpMenu) {
        return success(this.mpMenuService.updateById(mpMenu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.mpMenuService.removeByIds(idList));
    }
}