import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rsyb.fydao.data.DataDao;
import com.rsyb.fydao.user.UserDao;
import com.rsyb.fydao.user2.User2Dao;
import com.rsyb.fydomain.data.FyData;
import com.rsyb.fydomain.user.FyUser;
import com.rsyb.fydomain.user2.Fy2User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

//帮我们创建容器
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器使用的配置文件
@ContextConfiguration("classpath:ApplicationContext.xml")
public class Test01 {
    @Autowired
    private UserDao userDao;
    @Autowired
    private User2Dao user2Dao;
    @Autowired
    private DataDao dataDao;
    @Test
    public void test06(){
        for (int i = 40; i < 51;i++){
            FyData fyData = new FyData();
            fyData.setFyDid(UUID.randomUUID().toString().replaceAll("-",""));
            fyData.setFyUid("c5cdb262b5714e2fa31e75eb957f90b1");
            fyData.setFyName("兰兰"+i);
            fyData.setFyAge(i);
            fyData.setFyIdCard("XXYY0QQ00X"+i);
            fyData.setFySex("男");
            fyData.setFyStatus("QZ");
            fyData.setFyTdate(new Date());
            fyData.setFyProvince("北京");
            fyData.setFyCity("市辖区");
            fyData.setFyCounty("东城区");
            dataDao.insertOneData(fyData);
        }

    }

    @Test
    public void test05(){
        String pathOfImg = "\\0\\0\\";
        String pathOfImg2 = pathOfImg.replaceAll("\\\\", "/");
        System.out.println(pathOfImg2);

    }
    @Test
    public void test04(){
        Fy2User fy2User = new Fy2User();
        fy2User.setFy2Uid("5295e8b8cf714185b3591fbc5ced7eb6");
        fy2User.setFy2Img("//t.cn/2222222");
        user2Dao.updateUser2OfImg(fy2User);
    }
    @Test
    public void test03(){
        Fy2User fy2User = new Fy2User();
        fy2User.setFy2Uid("5295e8b8cf714185b3591fbc5ced7eb6");
        fy2User.setFy2Age("20");
        fy2User.setFy2Name("发");
        fy2User.setFy2Img("//t.cn/RCzsdCq");
        fy2User.setFy2Qm("防火防盗给");
        fy2User.setFy2Sex("男");
        user2Dao.updateUser2(fy2User);
    }
    @Test
    public void testfind2(){
        Fy2User fy2User = user2Dao.selectByFyUidy("1ea64f35420546e991e00346ae684369");
        System.out.println("----------------->"+fy2User);
    }
    @Test
    public void testfind(){
        Fy2User fy2User = user2Dao.selectByFy2Uid("5295e8b8cf714185b3591fbc5ced7eb6");
        System.out.println("----------------->"+fy2User);
    }
    @Test
    public void testSpring(){
        Fy2User fy2User = new Fy2User();
        fy2User.setFy2Age("18");
        fy2User.setFy2Name("good");
        fy2User.setFy2Img("//t.cn/RCzsdCq");
        fy2User.setFy2Qm("sbfdjgbnsfjbjfsghngjsfjb撒旦解放");
        fy2User.setFy2Sex("女");
        String fy2Uid = UUID.randomUUID().toString().replaceAll("-","");
        fy2User.setFy2Uid(fy2Uid);
        fy2User.setFyUidy("1ea64f35420546e991e00346ae684369");
        user2Dao.insertUser2(fy2User);
    }
}
