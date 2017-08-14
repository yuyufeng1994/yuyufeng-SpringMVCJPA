package solr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.yuyufeng.dao.BlogDao;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.solr.blog.BlogCore;
import top.yuyufeng.solr.blog.SolrBlogBean;
import top.yuyufeng.solr.blog.SolrBlogQuery;

import java.util.Date;
import java.util.List;

/**
 * Created by yuyufeng on 2017/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/applicationSolr.xml"})
public class BlogCoreQueryTest {

    @Autowired
    SolrBlogBean solrBlogBean;
    @Autowired
    SolrBlogQuery solrBlogQuery;

    @Test
    public void testFindByKeyWords() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        Page<Blog> page = solrBlogQuery.queryByKeyWords("体验",pageable);
        for (Blog blog : page.getContent()) {
            System.out.println(blog.toString());
        }
    }


    @Test
    public void testDelIndex() throws Exception {
        int res = solrBlogBean.deleteByQuery(4l);
        System.out.println(res);
    }

    @Test
    public void testAddIndex() throws Exception {
        BlogCore blogCore = new BlogCore();
        blogCore.setBlogId(4l);
        blogCore.setBlogTitle("solr是什么服务");
        blogCore.setBlogBrief("Solr是一个独立的企业级搜索应用服务器，它对外提供类似于Web-service的API接口。");
        blogCore.setBlogContent("Solr是一个独立的企业级搜索应用服务器，它对外提供类似于Web-service的API接口。用户可以通过http请求，向搜索引擎服务器提交一定格式的XML文件，生成索引；也可以通过Http Get操作提出查找请求，并得到XML格式的返回结果。");
        blogCore.setBlogImage("d://das");
        blogCore.setBlogUserName("yyf");
        blogCore.setUpdateTime(new Date());
        blogCore.setBlogCatalogNames("aa,bvb,cc");
        blogCore.setBlogCatalogIds("aa,bvb,cc");
        blogCore.setBlogUserId(2013l);
        int res = solrBlogBean.addIndex(blogCore);
        System.out.println(res);
    }


}
