package com.seasontemple.mproject.utils.custom;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.log.StaticLog;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 预设常量
 * @create: 2020/04/01 01:28:36
 */
public class NormalConstant {

    private NormalConstant() {
    }

/*    @Value("${encryptKey}")
    private static String encryptKey;

    public static Map<String, String> encrypt(String source) {
        if (encryptKey.equals("AES")) {
            byte[] aesKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
            AES aes = SecureUtil.aes(aesKey);
            return MapUtil.builder("salt", Arrays.toString(aesKey)).put("encrypt", aes.encryptHex(source)).build();
        } else {
            byte[] desKey = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
            SymmetricCrypto des = new SymmetricCrypto(SymmetricAlgorithm.DESede, desKey);
            return MapUtil.builder("salt", Arrays.toString(desKey)).put("encrypt", des.encryptHex(source)).build();
        }
    }

    public static Map<String, String> decrypt(String salt, String encrypt) {
         if (encryptKey.equals("AES")) {
             AES aes = SecureUtil.aes(salt.getBytes());
             return MapUtil.builder("key", salt).put("res", aes.decryptStr(encrypt, CharsetUtil.CHARSET_UTF_8)).build();

         } else {
             SymmetricCrypto des = new SymmetricCrypto(SymmetricAlgorithm.DESede, salt.getBytes());
             return MapUtil.builder("key", salt).put("res", des.decryptStr(encrypt)).build();
        }
    }*/

    /**
     * redis-success
     */
    public static final String OK = "success";

    /**
     * ttlMillis 默认Token有效时间
     */
    public static final long ttlMillis = 5 * 60 * 1000;

    /**
     * redis过期时间，以秒为单位，一分钟
     */
    public static final int EXRP_MINUTE = 60;

    /**
     * redis过期时间，以秒为单位，一小时
     */
    public static final int EXRP_HOUR = 60 * 60;

    /**
     * redis过期时间，以秒为单位，一天
     */
    public static final int EXRP_DAY = 60 * 60 * 24;

    /**
     * redis-key-前缀-shiro:cache:
     */
    public static final String PREFIX_SHIRO_CACHE = "shiro:cache:";

    /**
     * redis-key-前缀-shiro:access_token:
     */
    public static final String PREFIX_SHIRO_ACCESS_TOKEN = "shiro:access_token:";

    /**
     * redis-key-前缀-shiro:refresh_token:
     */
    public static final String PREFIX_SHIRO_REFRESH_TOKEN = "shiro:refresh_token:";

    /**
     * JWT-account:
     */
    public static final String ACCOUNT = "userName";

    /**
     * JWT-currentTimeMillis:
     */
//    public static final String CURRENT_TIME_MILLIS = "currentTimeMillis";
    public static final String CURRENT_TIME_MILLIS = "iat";

    /**
     * PASSWORD_MAX_LEN
     */
    public static final Integer PASSWORD_MAX_LEN = 20;

}
