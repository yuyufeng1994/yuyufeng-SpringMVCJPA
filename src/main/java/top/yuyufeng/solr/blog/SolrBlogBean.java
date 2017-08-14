package top.yuyufeng.solr.blog;

/**
 * Created by yuyufeng on 2017/5/24.
 */

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.yuyufeng.solr.SolrServer;
import top.yuyufeng.solr.blog.BlogCore;
import top.yuyufeng.utils.HtmlUtil;

import java.io.IOException;
import java.util.List;

@Repository
public class SolrBlogBean {
    @Autowired
    HttpSolrClient server;

    /**
     * 按Bean  添加/修改 索引
     *
     * @throws Exception
     */
    public int addIndex(BlogCore entity) throws Exception {
        entity.setBlogContent(HtmlUtil.deleteAllHTMLTag(entity.getBlogContent()));
        server.addBean(entity);
        UpdateResponse updateResponse = server.commit();
        return updateResponse.getStatus();
    }


    /**
     * 按Bean  添加/修改 索引
     *
     * @throws Exception
     */
    public int addIndexList(List<BlogCore> entitys) throws Exception {
        server.addBeans(entitys);
        UpdateResponse updateResponse = server.commit();
        return updateResponse.getStatus();

    }

    /**
     * 删除索引 按查询
     *
     * @throws Exception
     */
    public int deleteByQuery(String query) throws Exception {
        query = "*:*";
        server.deleteByQuery(query);
        server.commit();
        UpdateResponse updateResponse = server.commit();
        return updateResponse.getStatus();
    }

    /**
     * 删除索引 按id
     *
     * @throws Exception
     */
    public int deleteByQuery(Long id) throws Exception {
        server.deleteById(id + "");
        server.commit();
        UpdateResponse updateResponse = server.commit();
        return updateResponse.getStatus();
    }

    //查询索引
    public void query() throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setStart(0);//开始记录数
        query.setRows(10000);//总条数
        QueryResponse queryResponse = server.query(query);
        List<BlogCore> results = queryResponse.getBeans(BlogCore.class);
        System.out.println("总条数为：" + results.size());
        for (BlogCore entity : results) {
            System.out.println(entity.toString());
        }
    }

    public static void main(String[] args) throws IOException, SolrServerException {

    }

}