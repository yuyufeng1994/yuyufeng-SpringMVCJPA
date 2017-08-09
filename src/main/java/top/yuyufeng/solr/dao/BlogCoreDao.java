package top.yuyufeng.solr.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import top.yuyufeng.solr.entity.BlogCore;

/**
 * Created by yuyufeng on 2017/6/28.
 */
public interface BlogCoreDao extends SolrCrudRepository<BlogCore, String> {
    @Query(value = "*:*")
    Page<BlogCore> findAll(Pageable page);

    @Highlight(prefix = "<font style='color:red'>", postfix = "</font>")
    HighlightPage<BlogCore> findByKeywords(String keywords, Pageable page);
}
