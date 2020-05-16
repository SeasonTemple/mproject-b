package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.dto.EchartsDto;
import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.mapper.UserDetailMapper;
import com.seasontemple.mproject.service.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/05/14 23:58:16
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Override
    public UserDetail initUserDetail(String userName) {
        UserDetail userDetail = new LambdaQueryChainWrapper<>(userDetailMapper).eq(UserDetail::getUserName, userName).last("limit 1").one();
        AES aes = SecureUtil.aes(userDetail.getSalt());
        String deCryptPwd = aes.decryptStr(userDetail.getPassWord(), CharsetUtil.CHARSET_UTF_8);
        userDetail.setPassWord(deCryptPwd);
        return userDetail;
    }

    @Override
    public EchartsDto initChart(String id) {
        return null;
    }
}
