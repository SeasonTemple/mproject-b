package com.seasontemple.mproject.utils.tool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Season Temple
 * @program: mproject
 * @description:
 * @create: 2020/03/28 23:02:38
 */
@Component
public class TokenUtilImpl implements TokenUtil {

    private static final Log log = LogFactory.get();
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

    public static TokenUtilImpl build(String secretKey, SignatureAlgorithm signatureAlgorithm) {
        return StrUtil.isEmpty(secretKey) ? new TokenUtilImpl() : new TokenUtilImpl(secretKey, signatureAlgorithm);
    }

    @Override
    public String generate(Map<String, Object> claims, long ttlMillis) {
        // 签发时间（iat）：荷载部分的标准字段之一
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                // 荷载部分的非标准字段/附加字段，一般写在标准的字段之前。
                .setClaims(claims)
                // JWT ID（jti）：荷载部分的标准字段之一，JWT 的唯一性标识，虽不强求，但尽量确保其唯一性。
                .setId(Convert.toStr(claims.get("id")))
                // 签发时间（iat）：荷载部分的标准字段之一，代表这个 JWT 的生成时间。
                .setIssuedAt(now)
                // 签发人（iss）：荷载部分的标准字段之一，代表这个 JWT 的所有者。通常是 username 这样具有用户代表性的内容。
                .setSubject(getIss(Convert.toInt(claims.get("roleID"))) + claims.get("username"))
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
    public boolean verify(String jwtToken) {
        Algorithm algorithm = null;
        switch (signatureAlgorithm) {
            case HS256:
                algorithm = Algorithm.HMAC256(Base64.decodeBase64(EncodedSecretKey));
                break;
            default:
                throw new RuntimeException("不支持该算法");
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(jwtToken);  // 校验不通过会抛出异常
        return true;
    }

    @Override
    public Claims parse(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(EncodedSecretKey)
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    @Override
    public String getIss(int roleId) {
        return Iss.match(roleId);
    }

    /*public static void main(String[] args) {
        TokenUtil tokenUtil = TokenUtilImpl.build("", SignatureAlgorithm.HS256);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "d28dsadkjahf");
        claims.put("username", "12345");
        claims.put("password", "z31233213");
        claims.put("roleID", 11);
        String gToken = tokenUtil.generate(claims, 3000);
        Console.log(gToken);
        Console.log(gToken.length());
        Console.log(tokenUtil.verify(gToken));
        if (tokenUtil.verify(gToken)){
            Console.log(tokenUtil.parse(gToken));
        }
//        Console.log(tokenUtil.getIss(Convert.toInt(claims.get("roleID"))));
    }*/
}