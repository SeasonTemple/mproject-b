package com.seasontemple.mproject.web.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seasontemple.mproject.dao.entity.MpRequest;
import com.seasontemple.mproject.service.service.MpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 申请表(MpRequest)表控制层
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:07:20
 */
@RestController
@RequestMapping("mpRequest")
public class MpRequestController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MpRequestService mpRequestService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param mpRequest 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<MpRequest> page, MpRequest mpRequest) {
        return success(this.mpRequestService.page(page, new QueryWrapper<>(mpRequest)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.mpRequestService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mpRequest 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody MpRequest mpRequest) {
        return success(this.mpRequestService.save(mpRequest));
    }

    /**
     * 修改数据
     *
     * @param mpRequest 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody MpRequest mpRequest) {
        return success(this.mpRequestService.updateById(mpRequest));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.mpRequestService.removeByIds(idList));
    }
}