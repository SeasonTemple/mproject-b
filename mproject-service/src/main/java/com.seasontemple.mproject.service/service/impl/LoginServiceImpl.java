package com.seasontemple.mproject.service.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.dao.mapper.UserRoleMapper;
import com.seasontemple.mproject.service.service.LoginService;
import com.seasontemple.mproject.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/04/05 01:28:24
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private static Log log = LogFactory.get(LoginServiceImpl.class);

    private final MpUserMapper mpUserMapper;

    private final UserRoleMapper userRoleMapper;

    public LoginServiceImpl(MpUserMapper mpUserMapper, UserRoleMapper userRoleMapper) {
        this.mpUserMapper = mpUserMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public UserRole checkLogin(String username) {
//        LambdaQueryWrapper<UserRole> queryWrapper = Wrappers.lambdaQuery();
//        UserRole logUser = userRoleMapper.selectOne(queryWrapper.eq(UserRole::getUserName, username));
//        log.warn("{}", logUser);
        return Optional.ofNullable(new LambdaQueryChainWrapper<>(userRoleMapper)
                .select(UserRole::getUserName, UserRole::getPassWord, UserRole::getRoleId, UserRole::getSalt,UserRole::getAccountStatus,UserRole::getRoleState)
                .eq(UserRole::getUserName, username)
                .one()).orElse(new UserRole());
    }

    @Override
    public int register(MpUser mpUser) {
        byte[] salt = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        log.warn("{}",salt.length);
        AES aes = SecureUtil.aes(salt);
        mpUser.setSalt(salt);
        mpUser.setPassWord(aes.encryptHex(mpUser.getPassWord()));
//        mpUser.setCreateTime(new Date(System.currentTimeMillis()));
//        mpUser.setLastLogin(new Date());
//        mpUser.setRoleId(1);
//        mpUser.setStatus(0);
        return mpUserMapper.insert(mpUser);
    }

}
