package com.seasontemple.mproject.service.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.dto.UserMenu;
import com.seasontemple.mproject.dao.mapper.UserMenuMapper;
import com.seasontemple.mproject.service.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/04/18 15:30:29
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private UserMenuMapper userMenuMapper;


    @Override
    public List<UserMenu> initMenu() {

        return new LambdaQueryChainWrapper<>(userMenuMapper).list();
    }
}
