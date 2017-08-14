package top.yuyufeng.solr;

/**
 * Created by yuyufeng on 2017/5/24.
 */

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SolrServer {
    private HttpSolrClient server = null;
    @Value("${solr.host}")
    private String solrServerUrl;

    @Bean(name="httpSolrClient")
    public HttpSolrClient getServer() {
        if (server == null) {
            server = new HttpSolrClient(solrServerUrl);
            server.setDefaultMaxConnectionsPerHost(1000);
            server.setMaxTotalConnections(10000);//最大连接数
            server.setConnectionTimeout(60000);//设置连接超时时间（单位毫秒） 1000
            server.setSoTimeout(60000);//// 设置读数据超时时间(单位毫秒) 1000
            server.setFollowRedirects(false);//遵循从定向
            server.setAllowCompression(true);//允许压缩
        }
        return server;
    }
}