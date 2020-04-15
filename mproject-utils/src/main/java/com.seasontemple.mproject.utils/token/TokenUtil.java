package com.seasontemple.mproject.utils.token;


import cn.hutool.core.collection.CollUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.seasontemple.mproject.utils.exception.CustomException;
import io.jsonwebtoken.Claims;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author Season Temple
 * @program: mproject
 * @description: token相关工具类接口
 * @create: 2020/03/28 22:45:10
 */
public interface TokenUtil {

    static String ISDEFAULT = "st";

    enum Iss {
        ISUER("user", 1),
        ISADMIN("admin", 2),
        ISCUSTOM("custom", 3);

        private String userIss;
        private int roleId;
        private static final Iss[] values = Iss.values();

        Iss(String userIss, int roleId) {
            this.userIss = userIss;
            this.roleId = roleId;
        }

        public static String match(int roleId) {
            List<Iss> res = Arrays.stream(values).filter(s -> s.roleId == roleId).collect(Collectors.toList());
            return res.size() > 0 ? CollUtil.join(res, "-") : ISDEFAULT + "-";
        }
    }

    /**
     * 生成Token
     * @param claims
     * @return 生成的Token字符串
     */
    String generate(Map<String, Object> claims, long ttlMillis);

    /**
     * 验证token
     * @param jwtToken
     * @return 验证结果
     */
    boolean verify(String jwtToken);

    /**
     * Token加密
     * @return 加密后的Token
     *//*
    String encrypt();*/

    /**
     * 解析Token
     * @param token
     * @return token中的信息
     */
    Claims parse(String token);

    /**
     * 指定签发人前缀
     * @param roleId
     * @return 匹配的签发人前缀
     */
    String getIss(int roleId);

    /**
     * 获得Token中的信息无需secret解密也能获得
     *
     * @param token
     * @param claim
     * @return token中Claim的信息，以字符串形式返回
     */
    String getClaim(String token, String claim);

}
