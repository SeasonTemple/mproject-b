package com.seasontemple.mproject.web.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seasontemple.mproject.dao.entity.MpRoute;
import com.seasontemple.mproject.web.service.MpRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 路由表(MpRoute)表控制层
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:47:46
 */
@RestController
@RequestMapping("mpRoute")
public class MpRouteController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MpRouteService mpRouteService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param mpRoute 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<MpRoute> page, MpRoute mpRoute) {
        return success(this.mpRouteService.page(page, new QueryWrapper<>(mpRoute)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.mpRouteService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mpRoute 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody MpRoute mpRoute) {
        return success(this.mpRouteService.save(mpRoute));
    }

    /**
     * 修改数据
     *
     * @param mpRoute 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody MpRoute mpRoute) {
        return success(this.mpRouteService.updateById(mpRoute));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.mpRouteService.removeByIds(idList));
    }
}