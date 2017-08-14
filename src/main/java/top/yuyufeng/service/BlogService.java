package top.yuyufeng.service;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.yuyufeng.dao.BlogDao;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.entity.User;
import top.yuyufeng.solr.blog.BlogCore;
import top.yuyufeng.solr.blog.SolrBlogBean;

import java.util.List;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Service
public class BlogService extends BaseServiceAbstract<Blog> {

    @Autowired
    private SolrBlogBean solrBlogBean;

    @Override
    public Blog findOneById(Long id) {
        return blogDao.findOne(id);
    }

    @Override
    public Blog save(Blog entity) {
        return blogDao.save(entity);
    }

    @Override
    public void deleteOne(Long id) {
        blogDao.delete(id);
    }

    @Override
    public Page<Blog> findPage(Pageable pageable) {
        if (pageable == null) {
            List<Blog> list = IteratorUtils.toList(blogDao.findAll().iterator());
            Page<Blog> page = new PageImpl<Blog>(list);
            return page;
        }
        return blogDao.findAll(pageable);
    }

    public Page<Blog> findPageByBlogStatus(List<String> blogStatuses, Pageable pageable) {
        if (pageable == null) {
            List<Blog> list = IteratorUtils.toList(blogDao.findByBlogStatusIn(blogStatuses, pageable).iterator());
            Page<Blog> page = new PageImpl<Blog>(list);
            return page;
        }
        return blogDao.findByBlogStatusIn(blogStatuses, pageable);
    }

    public Page<Blog> findBlogPageByCatalogId(List<String> blogStatuses, Long catalogId, Pageable pageable) {
        Page<Blog> page = blogDao.findBlogPageByCatalogId(blogStatuses, catalogId, pageable);
        return page;
    }

    /**
     * 增加\修改一篇文章索引
     *
     * @param blogId
     * @return
     * @throws Exception
     */
    public int indexCreate(Long blogId) throws Exception {
        Blog blog = findOneById(blogId);
        BlogCore blogCore = new BlogCore();
        blogCore.setBlogId(blog.getBlogId());
        blogCore.setBlogTitle(blog.getBlogTitle());
        blogCore.setBlogBrief(blog.getBlogBrief());
        blogCore.setBlogContent(blog.getBlogContent());
        blogCore.setBlogImage(blog.getBlogImage());
        blogCore.setBlogUserName(blog.getBlogUser().getUserName());
        blogCore.setUpdateTime(blog.getUpdateTime());
        String catalogNames = "";
        String catalogIds = "";

        for (Catalog catalog : blog.getCatalogs()) {
            catalogNames = catalogNames + catalog.getCatalogName() + ",";
            catalogIds = catalogIds + catalog.getCatalogId() + ",";
        }

        blogCore.setBlogCatalogNames(catalogNames);
        blogCore.setBlogCatalogIds(catalogIds);

        blogCore.setBlogUserId(blog.getBlogUser().getUserId());
        return solrBlogBean.addIndex(blogCore);
    }

    public int indexDelete(Long blogId) throws Exception {
        return solrBlogBean.deleteByQuery(blogId);
    }
}
