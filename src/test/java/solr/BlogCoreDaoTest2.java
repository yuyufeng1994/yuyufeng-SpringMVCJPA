package solr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.yuyufeng.solr.entity.BlogCore;

import java.util.Date;

/**
 * Created by yuyufeng on 2017/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationSolr.xml"})
public class BlogCoreDaoTest2 {


    @Test
    public void testFind() {
        System.out.println("BlogCoreDaoTest2.testFind");
        /*Pageable pageable = new PageRequest(0, 5);
        blogCoreDao.findAll(pageable);*/
    }

    @Test
    public void testInsert() {
        BlogCore blogCore = new BlogCore();
        blogCore.setBlogId("2");
        blogCore.setBlogTitle("这是标题");
        blogCore.setBlogBrief("简介");
        blogCore.setBlogContent("主题blog.getBlogContent()");
        blogCore.setBlogImage("d://das");
        blogCore.setBlogUserName("yyf");
        blogCore.setUpdateTime(new Date());
        blogCore.setBlogCatalogs("aa,bvb,cc");
        blogCore.setBlogUserId("2013");
//        blogCoreDao.save(blogCore);
    }

}
