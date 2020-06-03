package com.seasontemple.mproject.service.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.dto.AuthDto;
import com.seasontemple.mproject.dao.dto.RoleAuth;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.entity.MpMenu;
import com.seasontemple.mproject.dao.entity.MpRole;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.dao.mapper.MpMenuMapper;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.dao.mapper.RoleAuthMapper;
import com.seasontemple.mproject.dao.mapper.UserRoleMapper;
import com.seasontemple.mproject.service.service.LoginService;
import com.seasontemple.mproject.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/04/05 01:28:24
 */
@Service
//@Transactional
public class LoginServiceImpl implements LoginService {

    private static Log log = LogFactory.get(LoginServiceImpl.class);

    private final MpUserMapper mpUserMapper;

    private final UserRoleMapper userRoleMapper;

    private final MpMenuMapper mpMenuMapper;

    private final RoleAuthMapper roleAuthMapper;

    public LoginServiceImpl(MpUserMapper mpUserMapper, UserRoleMapper userRoleMapper, MpMenuMapper mpMenuMapper, RoleAuthMapper roleAuthMapper) {
        this.mpUserMapper = mpUserMapper;
        this.userRoleMapper = userRoleMapper;
        this.mpMenuMapper = mpMenuMapper;
        this.roleAuthMapper = roleAuthMapper;
    }

    @Override
    public UserRole checkLogin(String username) {
//        LambdaQueryWrapper<UserRole> queryWrapper = Wrappers.lambdaQuery();
//        UserRole logUser = userRoleMapper.selectOne(queryWrapper.eq(UserRole::getUserName, username));
//        log.warn("{}", logUser);
        return Optional.ofNullable(new LambdaQueryChainWrapper<>(userRoleMapper)
                .select(UserRole::getUserName, UserRole::getPassWord, UserRole::getRoleId, UserRole::getSalt, UserRole::getAccountStatus, UserRole::getRoleState)
                .eq(UserRole::getUserName, username)
                .one()).orElse(new UserRole());
    }

    @Override
    public int register(MpUser mpUser) {
        byte[] salt = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        log.warn("{}", salt.length);
        AES aes = SecureUtil.aes(salt);
        mpUser.setSalt(salt);
        mpUser.setPassWord(aes.encryptHex(mpUser.getPassWord()));
//        mpUser.setCreateTime(new Date(System.currentTimeMillis()));
//        mpUser.setLastLogin(new Date());
//        mpUser.setRoleId(1);
//        mpUser.setStatus(0);
        return Optional.of(mpUserMapper.insert(mpUser)).orElseThrow(() -> new CustomException("账户已存在！请重新输入。"));
    }

    @Override
    public int refresh() {
        return mpUserMapper.selectCount(null);
    }

    @Override
    public AuthDto getRole(String userName) {
        UserRole role = new LambdaQueryChainWrapper<>(userRoleMapper).select(UserRole::getRoleName).eq(UserRole::getUserName, userName).one();
        String val = role.getRoleName();
        log.warn("val: {}", val);
        List<RoleAuth> auth = new LambdaQueryChainWrapper<>(roleAuthMapper).select(RoleAuth::getName, RoleAuth::getAuth, RoleAuth::getPermission).eq(RoleAuth::getName, val).list();
        log.warn("auth: {}", auth);
        List<MpMenu> menus = new LambdaQueryChainWrapper<>(mpMenuMapper).like(MpMenu::getPermission, val).list();
        log.warn("menus: {}", menus);
        return AuthDto.builder().roleName(val).auth(auth).menus(menus).build();
    }

}
