package dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.yuyufeng.dao.BlogDao;
import top.yuyufeng.dao.CatalogDao;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yuyufeng on 2017/8/1.
 */
public class BlogDaoTest extends BaseDaoTest {
    @Autowired
    private BlogDao blogDao;

    @Test
    public void test() {
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserId(2l);

        Set<Catalog> catalogs = new HashSet<>();
        Catalog ca1 = new Catalog();
        ca1.setCatalogId(1l);
        catalogs.add(ca1);

        Catalog ca2 = new Catalog();
        ca2.setCatalogId(2l);
        catalogs.add(ca2);

//        for (int i = 0; i < 10; i++) {
            Blog blog = new Blog();
            blog.setBlogTitle("博客标题");
            blog.setBlogBrief("博客简介");
            blog.setBlogContent("博客内容");
            blog.setBlogImage("博客图片地址");
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
//            blog.setBlogUser(user);

            blog.setCatalogs(catalogs);
            blogDao.save(blog);
            System.out.println(blog);
//        }
    }
}
