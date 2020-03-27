package com.seasontemple.mproject.web.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seasontemple.mproject.dao.entity.MpAuthority;
import com.seasontemple.mproject.service.service.MpAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 权限表(MpAuthority)表控制层
 *
 * @author SeasonTemple
 * @since 2020-03-27 21:07:20
 */
@RestController
@RequestMapping("mpAuthority")
public class MpAuthorityController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MpAuthorityService mpAuthorityService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param mpAuthority 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<MpAuthority> page, MpAuthority mpAuthority) {
        return success(this.mpAuthorityService.page(page, new QueryWrapper<>(mpAuthority)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.mpAuthorityService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mpAuthority 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody MpAuthority mpAuthority) {
        return success(this.mpAuthorityService.save(mpAuthority));
    }

    /**
     * 修改数据
     *
     * @param mpAuthority 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody MpAuthority mpAuthority) {
        return success(this.mpAuthorityService.updateById(mpAuthority));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.mpAuthorityService.removeByIds(idList));
    }
}