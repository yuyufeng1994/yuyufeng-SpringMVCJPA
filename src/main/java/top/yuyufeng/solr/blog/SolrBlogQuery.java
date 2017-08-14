package top.yuyufeng.solr.blog;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.entity.User;
import top.yuyufeng.solr.SolrServer;

import java.util.*;

/**
 * Created by yuyufeng on 2017/8/14.
 */
@Repository
public class SolrBlogQuery {
    @Autowired
    HttpSolrClient server;

    //查询索引
    public  Page<Blog> queryByKeyWords(String keywords, Pageable pageable) throws Exception {
        SolrQuery query = new SolrQuery();
        query.set("q", "keywords:" + keywords);//*通配多个字符
//        query.set("sort", "product_price desc");
        //======高亮设置===
        //开启高亮
        query.setHighlight(true);
        //高亮域
        query.addHighlightField("blogContent");
        query.addHighlightField("blogTitle");
        query.addHighlightField("blogBrief");
        query.addHighlightField("blogUserName");
        //前缀
        query.setHighlightSimplePre("<span style='color:red'>");
        //后缀
        query.setHighlightSimplePost("</span>");
        //query.setHighlightSnippets(1);//结果分片数，默认为1
        //query.setHighlightFragsize(1000);//每个分片的最大长度，默认为100

        query.setStart(pageable.getPageNumber());//开始记录数
        query.setRows(pageable.getPageSize());//总条数
        QueryResponse queryResponse = server.query(query);
        List<BlogCore> results = queryResponse.getBeans(BlogCore.class);
        System.out.println("总条数为：" + results.size());
        //输出高亮
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        List<Blog> blogs = new ArrayList<>();
        for (BlogCore result : results) {
            Map<String, List<String>> map = highlighting.get(result.getBlogId()+"");

            List<String> list = map.get("blogTitle");
            if (list != null && list.size() > 0) {
                result.setBlogTitle(list.get(0));
            }

            list = map.get("blogBrief");
            if (list != null && list.size() > 0) {
                result.setBlogBrief(list.get(0));
            }else{
                list = map.get("blogContent");
                if (list != null && list.size() > 0) {
                    result.setBlogBrief(list.get(0));
                }
            }

            list = map.get("blogContent");
            if (list != null && list.size() > 0) {
                result.setBlogContent(list.get(0));
            }

            list = map.get("blogUserName");
            if (list != null && list.size() > 0) {
                result.setBlogUserName(list.get(0));
            }


            //类型转换
            Blog blog = new Blog();
            blog.setBlogId(result.getBlogId());
            blog.setBlogTitle(result.getBlogTitle());
            blog.setBlogBrief(result.getBlogBrief());
            blog.setBlogImage(result.getBlogImage());
            blog.setBlogContent(result.getBlogContent());
            blog.setUpdateTime(result.getUpdateTime());
            User user = new User();
            user.setUserId(result.getBlogUserId());
            user.setUserName(result.getBlogUserName());
            blog.setBlogUser(user);

            if (!StringUtils.isEmpty(result.getBlogCatalogIds()) && !StringUtils.isEmpty(result.getBlogCatalogNames())) {
                String[] catalogIds = result.getBlogCatalogIds().split(",");
                String[] catalogNames = result.getBlogCatalogNames().split(",");
                try {
                    if (catalogIds.length == catalogNames.length) {
                        Set<Catalog> catalogs = new HashSet<>();
                        for (int i = 0; i < catalogIds.length; i++) {
                            Catalog catalog = new Catalog();
                            catalog.setCatalogId(Long.valueOf(catalogIds[i]));
                            catalog.setCatalogName(catalogNames[i]);
                            catalogs.add(catalog);
                        }
                        blog.setCatalogs(catalogs);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            blogs.add(blog);
        }
        return new PageImpl<Blog>(blogs, pageable, results.size());
    }

    public static void main(String[] args) throws Exception {
      /*  Pageable pageable = new PageRequest(0, 10);
        Page<Blog> page = queryByKeyWords("体验", pageable);
        for (Blog blog : page.getContent()) {
            System.out.println(blog.toString());
        }*/
    }
}
