package com.seasontemple.mproject.service.service;

import com.seasontemple.mproject.dao.dto.EchartsDto;
import com.seasontemple.mproject.dao.dto.UserDetail;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 主页业务层接口
 */
public interface HomeService {

    UserDetail initUserDetail(String userName);

    EchartsDto initChart(String id);
}
