import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
    public void test() {
//        MpUser user = new MpUser();
//        user.setUserName("toString2");
//        boolean update = new LambdaUpdateChainWrapper<>(mpUserMapper).eq(MpUser::getUserName, user.getUserName()).set(MpUser::getUserName, "toString2").set(true,MpUser::getLastLogin, null).update();
//        int a = mpUserMapper.insert(user);
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        Instant instant = Instant.ofEpochSecond(now.toEpochSecond(ZoneOffset.ofHours(8)));
//        Date date = Date.from(instant);
//        log.warn("{}", update);
//        boolean a = new LambdaUpdateChainWrapper<>(mpUserMapper).eq(MpUser::getUserName, "toString21").set(MpUser::getUserName, "toString2").update();
//        System.out.println(a);
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
