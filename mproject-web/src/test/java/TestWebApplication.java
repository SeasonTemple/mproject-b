import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seasontemple.mproject.dao.entity.MpUser;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.service.service.MpUserService;
import com.seasontemple.mproject.utils.custom.ResultCode;
import com.seasontemple.mproject.utils.exception.CustomException;
import com.seasontemple.mproject.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Optional;


/**
 * @author Season Temple
 * @program: mproject
 * @description: Web模块测试类
 * @create: 2020/03/28 00:26:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WebAppConfiguration("src/main/resource")
public class TestWebApplication {

    private static Log log = LogFactory.get(TestWebApplication.class);

    @Autowired
    private MpUserMapper mpUserMapper;

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

}
