import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.dao.redis.JedisUtil;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;


/**
 * @author Season Temple
 * @program: mproject
 * @description: Web模块测试类
 * @create: 2020/03/28 00:26:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class TestWebApplication {

    private static Log log = LogFactory.get(TestWebApplication.class);

    @Autowired
    private MpUserMapper mpUserMapper;

//    @Value("${refreshTokenExpireTime}")
//    private static String refreshTokenExpireTime;

//    @Test
//    public void test() {
//        MpUser mpUser = new MpUser();
//        mpUser.setUserName("q12232");
//        MpUser loginUser = new LambdaQueryChainWrapper<>(mpUserMapper)
//                .select(MpUser::getUserId, MpUser::getUserName, MpUser::getPassWord, MpUser::getSalt)
//                .ge(MpUser::getUserName, mpUser.getUserName())
//                .one();
//        log.info("结果为：{}", loginUser);
//    }

   /* @Test
    public void test() {
        String content = "test卓哥";
        Map aa = encryptSource(content);
        StaticLog.warn("{}|{}", aa.get("salt"), aa.get("encrypt"));
        Map bb;
        bb = decryptSource(StrUtil.bytes(aa.get("salt").toString()), StrUtil.toString(aa.get("encrypt")));
        StaticLog.warn("{}", aa.get("decrypt"));
    }

    public static Map encryptSource(String source) {
        byte[] salt = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        AES aes = SecureUtil.aes(salt);
        String encrypt = aes.encryptHex(source);
        return MapUtil.builder("salt", StrUtil.toString(salt)).put("encrypt", encrypt).build();
    }

    public Map decryptSource(AES aes, byte[] salt, String encrypt) {
        return MapUtil.builder("decrypt", aes.decryptStr(encrypt, CharsetUtil.CHARSET_UTF_8)).build();
    }*/

}
