package com.seasontemple.mproject.service.service;

import com.seasontemple.mproject.dao.dto.UserMenu;

import java.util.List;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 菜单生成业务层
 * @create: 2020/04/18 15:27:17
 */
public interface MenuService {

    List<UserMenu> initMenu();
}
