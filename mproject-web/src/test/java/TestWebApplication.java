import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.seasontemple.mproject.dao.dto.UserDetail;
import com.seasontemple.mproject.dao.dto.UserRole;
import com.seasontemple.mproject.dao.mapper.MpUserMapper;
import com.seasontemple.mproject.dao.mapper.UserDetailMapper;
import com.seasontemple.mproject.dao.mapper.UserRoleMapper;
import com.seasontemple.mproject.web.WebApplication;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;


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
//    private UserDetailMapper userDetailMapper;
//    private MpUserMapper mpUserMapper;

    @Test
    public void test() {
//        UserDetail userDetail = new LambdaQueryChainWrapper<UserDetail>(userDetailMapper).eq(UserDetail::getUserName, "toString2").one();
//        log.warn("{}", userDetail);

//        UserDetail userDetail = mpUserMapper.getUserDetail("toString2");
//        log.warn("{}", userDetail);
//        StringBuffer tableId = new StringBuffer("user-");
//        tableId.append(IdUtil.objectId());
//        Console.log(IdUtil.objectId().length());
    }

}
