package solr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yuyufeng on 2017/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/appSolr.xml"})
public class BlogCoreDaoTest2 {


    @Test
    public void testFind() {
        System.out.println("BlogCoreDaoTest2.testFind");
        /*Pageable pageable = new PageRequest(0, 5);
        blogCoreDao.findAll(pageable);*/
    }

    @Test
    public void testInsert() {

       /* BlogCore blogCore = new BlogCore();
        blogCore.setBlogId(blog.getBlogId());
        blogCore.setBlogBrief(blog.getBlogBrief());
        blogCore.setBlogContent(blog.getBlogContent());
        blogCore.setBlogImage(blog.getBlogImage());
        blogCore.setBlogUserName(blog.getBlogUser().getUserName());
        blogCore.setUpdateTime(blog.getUpdateTime());
        blogCoreDao.save(blogCore);*/
    }

}
