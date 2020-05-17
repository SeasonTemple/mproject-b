import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.seasontemple.mproject.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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

//    @Autowired
//    private MpDepartmentMapper mpDepartmentMapper;


//    @Autowired
//    private ProfileService profileService;

    @Test
    public void test() {
//        List<MpDepartment> departmentList = new LambdaQueryChainWrapper<>(mpDepartmentMapper).list();
//        List<MpProject> projectList = new LambdaQueryChainWrapper<>(mpProjectMapper).list();
//        log.warn("{}", MapUtil.builder().put("departments", departmentList).put("projects", projectList).build());
//        UserDetail userDetail = new LambdaQueryChainWrapper<UserDetail>(userDetailMapper).eq(UserDetail::getUserName, "toString2").one();
//        UserDetail userDetail = mpUserMapper.getUserDetail("toString2");
//        StringBuffer tableId = new StringBuffer("user-");
//        tableId.append(IdUtil.objectId());
//        Console.log(IdUtil.objectId().length());
    }

}
