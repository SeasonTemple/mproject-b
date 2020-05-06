import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
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
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.dao.redis.JedisUtil;
import com.seasontemple.mproject.utils.custom.NormalConstant;
import com.seasontemple.mproject.utils.email.EmailSender;
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

    @Test
    public void test() {
//        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
//        // 自定义验证码内容为四则运算方式
//        captcha.setGenerator(new MathGenerator());
//        // 重新生成code
//        captcha.createCode();
//        captcha.write("d:/image/shear.png");
    }

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
