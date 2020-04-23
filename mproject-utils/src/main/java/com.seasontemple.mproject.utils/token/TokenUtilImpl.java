package com.seasontemple.mproject.utils.token;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
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
    // 默认token有效时间
    private static final long ttlMillis = NormalConstant.ttlMillis;

    private final byte[] EncodedSecretKey;

    private SignatureAlgorithm signatureAlgorithm;

    private TokenUtilImpl() {
        this.EncodedSecretKey = Base64.encodeBase64(defaultEncodedSecretKey.getBytes());
        // 默认加密算法
        this.signatureAlgorithm = SignatureAlgorithm.HS256;
    }

    private TokenUtilImpl(String secretKey) {
        this.EncodedSecretKey = Base64.encodeBase64(secretKey.getBytes());
        this.signatureAlgorithm = SignatureAlgorithm.HS256;
    }

    public static TokenUtilImpl build(String secretKey) {
        return StrUtil.isEmpty(secretKey) ? new TokenUtilImpl() : new TokenUtilImpl(secretKey);
    }

    @Override
    public String generate(Map<String, Object> claims, long ttl) {
        long nowMillis = (long) claims.remove("iat");
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                // 荷载部分的非标准字段/附加字段，一般写在标准的字段之前。
                .setClaims(claims)
                // JWT ID（jti）：荷载部分的标准字段之一，JWT 的唯一性标识，虽不强求，但尽量确保其唯一性。
                .setId(StrUtil.toString(nowMillis)+ claims.get("userName"))
                // 签发时间（iat）：荷载部分的标准字段之一，代表这个 JWT 的生成时间。
                .setIssuedAt(now)
                .setIssuer(claims.get("userName").toString())
                // 签发人（iss）：荷载部分的标准字段之一，代表这个 JWT 的所有者。通常是 username 这样具有用户代表性的内容。
                .setSubject(getIss(Convert.toInt(claims.get("roleId"))) + "-" + claims.get("userName"))
                // 设置生成签名的算法和秘钥
                .signWith(signatureAlgorithm, EncodedSecretKey);
//        if (ttl >= 0) {
//        long expMillis = 0;
        long expMillis = (ttl >= 0) ? (nowMillis + ttl) : (nowMillis + ttlMillis);
        Date exp = new Date(expMillis);
        // 过期时间（exp）：荷载部分的标准字段之一，代表这个 JWT 的有效期。
        builder.setExpiration(exp);
        return builder.compact();
    }

    @Override
    public boolean verify(String jwtToken) {
        Algorithm algorithm = null;
        if (signatureAlgorithm == SignatureAlgorithm.HS256) {
//            String secret = getClaim(jwtToken, NormalConstant.ACCOUNT) + EncodedSecretKey;
            algorithm = Algorithm.HMAC256(EncodedSecretKey);
        } else {
            throw new RuntimeException("不支持该算法");
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(jwtToken);
        } catch (TokenExpiredException e) {
            throw new CustomException("您的令牌已过期，请重新登录！！");
        }
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

    @Override
    public String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null、
            return claim.equals("iat") ? jwt.getClaims().get(claim).asLong().toString() : jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
//            log.error("解密Token中的公共信息出现JWTDecodeException异常:{}", e.getMessage());
            throw new CustomException("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        TokenUtil tokenUtil = TokenUtilImpl.build("demoData");
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userName", "demoData");
//        claims.put("passWord", "demoData");
//        claims.put("roleId", 1);
//        String gToken = tokenUtil.generate(claims, 100000);
//        Console.log(tokenUtil.parse(gToken));
//        if (tokenUtil.verify(gToken)) {
//            Console.log("验证成功!");
//        }
//        log.info("{}", tokenUtil.getClaim(gToken, "iat"));
//    }
}