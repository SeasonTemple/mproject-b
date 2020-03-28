package com.seasontemple.mproject.utils.tool;

import com.seasontemple.mproject.dao.entity.MpUser;


/**
 * @author Season Temple
 * @program: mproject
 * @description: token相关工具类接口
 * @create: 2020/03/28 22:45:10
 */
public interface TokenUtil {

    static String ISSUER = "Season Temple";

    /**
     * 生成Token
     *
     * @return Token
     */
    String generate(MpUser userBean, long ttlMillis);

    /**
     * Token校验
     *
     * @return 校验结果
     */
    boolean verify();

    /**
     * Token加密
     *
     * @return 加密后的Token
     */
    String encrypt();

    /**
     * Token解析
     *
     * @return 原始Token
     */
    String parse(String token);

}
