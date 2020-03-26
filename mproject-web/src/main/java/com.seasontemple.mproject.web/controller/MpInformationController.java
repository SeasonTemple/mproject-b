package com.seasontemple.mproject.web.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seasontemple.mproject.dao.entity.MpInformation;
import com.seasontemple.mproject.web.service.MpInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 系统消息表(MpInformation)表控制层
 *
 * @author SeasonTemple
 * @since 2020-03-26 21:47:46
 */
@RestController
@RequestMapping("mpInformation")
public class MpInformationController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MpInformationService mpInformationService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param mpInformation 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<MpInformation> page, MpInformation mpInformation) {
        return success(this.mpInformationService.page(page, new QueryWrapper<>(mpInformation)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.mpInformationService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mpInformation 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody MpInformation mpInformation) {
        return success(this.mpInformationService.save(mpInformation));
    }

    /**
     * 修改数据
     *
     * @param mpInformation 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody MpInformation mpInformation) {
        return success(this.mpInformationService.updateById(mpInformation));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.mpInformationService.removeByIds(idList));
    }
}