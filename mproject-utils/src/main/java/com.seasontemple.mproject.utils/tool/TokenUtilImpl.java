package com.seasontemple.mproject.utils.tool;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.seasontemple.mproject.dao.entity.MpUser;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/03/28 23:02:38
 */
public class TokenUtilImpl implements TokenUtil {
    // 默认密匙
    private static final String defaultEncodedSecretKey = "seasontemple";
    // 默认加密算法
    private static final SignatureAlgorithm defaultSignatureAlgorithm = SignatureAlgorithm.HS256;

    private TokenUtilImpl() {
        this.EncodedSecretKey = defaultEncodedSecretKey;
        this.signatureAlgorithm = defaultSignatureAlgorithm;
    }

    private final String EncodedSecretKey;

    private final SignatureAlgorithm signatureAlgorithm;

    private TokenUtilImpl(String secretKey, SignatureAlgorithm signatureAlgorithm) {
        this.EncodedSecretKey = Base64.encodeBase64String(secretKey.getBytes());
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public TokenUtilImpl build(String secretKey, SignatureAlgorithm signatureAlgorithm) {
        return StrUtil.isEmpty(secretKey) ? new TokenUtilImpl() : new TokenUtilImpl(secretKey, signatureAlgorithm);
    }

    @Override
    public String generate(MpUser userBean, long ttlMillis) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userBean.getUserName());
        claims.put("password", userBean.getPassWord());
        claims.put("role", userBean.getRoleId());
        // 签发时间（iat）：荷载部分的标准字段之一
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                // 荷载部分的非标准字段/附加字段，一般写在标准的字段之前。
                .setClaims(claims)
                // JWT ID（jti）：荷载部分的标准字段之一，JWT 的唯一性标识，虽不强求，但尽量确保其唯一性。
                .setId(IdUtil.simpleUUID())
                // 签发时间（iat）：荷载部分的标准字段之一，代表这个 JWT 的生成时间。
                .setIssuedAt(now)
                // 签发人（iss）：荷载部分的标准字段之一，代表这个 JWT 的所有者。通常是 username 这样具有用户代表性的内容。
                .setSubject(ISSUER + userBean.getUserName())
                // 设置生成签名的算法和秘钥
                .signWith(signatureAlgorithm, EncodedSecretKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            // 过期时间（exp）：荷载部分的标准字段之一，代表这个 JWT 的有效期。
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    @Override
    public boolean verify() {
        return false;
    }

    @Override
    public String encrypt() {
        return null;
    }

    @Override
    public String parse(String token) {
        return null;
    }
}