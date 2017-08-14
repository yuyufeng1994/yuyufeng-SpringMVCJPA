package solr;

import dao.BaseDaoTest;
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
import top.yuyufeng.solr.blog.SolrBlogBean;
import top.yuyufeng.solr.blog.BlogCore;
import top.yuyufeng.solr.blog.SolrBlogQuery;
import top.yuyufeng.utils.HtmlUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by yuyufeng on 2017/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/applicationDatabase.xml","classpath:spring/applicationSolr.xml"})
public class BlogCoreBeanTest {
    @Autowired
    private BlogDao blogDao;


    @Autowired
    SolrBlogBean solrBlogBean;
    @Autowired
    SolrBlogQuery solrBlogQuery;


    @Test
    @Transactional
    public void testAddIndexList() throws Exception {
        List<Blog> blogs = (List<Blog>) blogDao.findAll();
        for (int i = 0; i < blogs.size(); i++) {
            BlogCore blogCore = new BlogCore();
            blogCore.setBlogId(blogs.get(i).getBlogId());
            blogCore.setBlogTitle(blogs.get(i).getBlogTitle());
            blogCore.setBlogBrief(blogs.get(i).getBlogBrief());
            blogCore.setBlogContent(blogs.get(i).getBlogContent());
            blogCore.setBlogImage(blogs.get(i).getBlogImage());
            blogCore.setBlogUserName(blogs.get(i).getBlogUser().getUserName());
            blogCore.setUpdateTime(blogs.get(i).getUpdateTime());
            String catalogNames = "";
            String catalogIds = "";

            for (Catalog catalog : blogs.get(i).getCatalogs()) {
                catalogNames = catalogNames + catalog.getCatalogName() + ",";
                catalogIds = catalogIds + catalog.getCatalogId() + ",";
            }

            blogCore.setBlogCatalogNames(catalogNames);
            blogCore.setBlogCatalogIds(catalogIds);

            blogCore.setBlogUserId(blogs.get(i).getBlogUser().getUserId());
            int res = solrBlogBean.addIndex(blogCore);
            System.out.println("索引成功" + blogCore.getBlogId() + " " + res);
            Thread.sleep(500);
        }
    }


    @Test
    public void testFind() throws Exception {
        solrBlogBean.query();
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


    @Test
    public void testFindAll() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        Page<Blog> page = solrBlogQuery.queryByKeyWords("体验",pageable);
        for (Blog blog : page.getContent()) {
            System.out.println(blog.toString());
        }
    }


}
