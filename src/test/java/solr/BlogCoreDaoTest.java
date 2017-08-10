package solr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.yuyufeng.dao.BlogDao;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.solr.dao.BlogCoreDao;
import top.yuyufeng.solr.entity.BlogCore;

import java.util.Date;

/**
 * Created by yuyufeng on 2017/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml", "classpath:spring/applicationSolr.xml"})
public class BlogCoreDaoTest {
    @Autowired
   private BlogCoreDao blogCoreDao;
    @Autowired
    private BlogDao blogDao;
    @Test
    public void testFind() {
        Pageable pageable = new PageRequest(0, 5);
        blogCoreDao.findAll(pageable);
    }

    @Test
    public void testInsert() {
        Blog blog = blogDao.findOne(114l);
        System.out.println(blog);
        BlogCore blogCore = new BlogCore();
        blogCore.setBlogId("1");
        blogCore.setBlogTitle("这是标题");
        blogCore.setBlogBrief("简介");
        blogCore.setBlogContent("主题blog.getBlogContent()");
        blogCore.setBlogImage("d://das");
        blogCore.setBlogUserName("yyf");
        blogCore.setUpdateTime(new Date());
        blogCore.setBlogCatalogs("aa,bvb,cc");
        blogCore.setBlogUserId("2013");
        blogCoreDao.save(blogCore);
    }

}
