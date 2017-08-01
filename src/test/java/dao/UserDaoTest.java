package dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.yuyufeng.constants.UserSatusEnum;
import top.yuyufeng.dao.UserDao;
import top.yuyufeng.entity.User;

import java.util.Date;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void test(){
        System.out.println("UserDao.test");
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUserName("yyf");
        user.setUserAccount("201701");
        user.setUserPassword("aaaa123");
        user.setUserStatus(UserSatusEnum.NORMAL.getKey());
        user.setCreateTime(new Date());
        userDao.save(user);
    }
}
