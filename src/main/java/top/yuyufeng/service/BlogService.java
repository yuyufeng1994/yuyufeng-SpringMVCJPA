package top.yuyufeng.service;

import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.rmi.runtime.Log;
import top.yuyufeng.constants.BlogStatusEnum;
import top.yuyufeng.constants.StatusesCommonUse;
import top.yuyufeng.dao.BlogDao;
import top.yuyufeng.dto.CataLogDto;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.entity.User;
import top.yuyufeng.solr.blog.BlogCore;
import top.yuyufeng.solr.blog.SolrBlogBean;
import top.yuyufeng.solr.blog.SolrBlogQuery;
import top.yuyufeng.utils.HtmlUtil;
import top.yuyufeng.utils.SessionUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Service
public class BlogService extends BaseServiceAbstract<Blog> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Page<Blog>> valueOs;
    @Autowired
    private SolrBlogBean solrBlogBean;

    @Autowired
    private SolrBlogQuery solrBlogQuery;

    @Override
    public Blog findOneById(Long id) {
        return blogDao.findOne(id);
    }

    @Override
    public Blog save(Blog entity) {
        entity.setUpdateTime(new Date());
        if (StringUtils.isEmpty(entity.getBlogStatus())) {
            entity.setBlogStatus(BlogStatusEnum.MODIFYING.getKey());
        }
        return blogDao.save(entity);
    }

    @Override
    public void deleteOne(Long id) {
        blogDao.delete(id);
    }

    @Override
    public Page<Blog> findPage(Pageable pageable) {
        Page<Blog> page = null;
        if (pageable == null) {
            List<Blog> list = IteratorUtils.toList(blogDao.findAll().iterator());
            page = new PageImpl<Blog>(list);
            return page;
        }
        page = blogDao.findAll(pageable);
        return page;
    }

    public Page<Blog> findPageByBlogStatus(List<String> blogStatuses, Pageable pageable) {
        Page<Blog> page = null;
        if (pageable == null) {
            List<Blog> list = IteratorUtils.toList(blogDao.findByBlogStatusIn(blogStatuses, pageable).iterator());
            page = new PageImpl<Blog>(list);
            return page;
        }
        try {
            page = valueOs.get("blog-page-" + pageable.getPageNumber() + "-" + pageable.getPageSize() + "-" + pageable.getSort());
        } catch (Exception e) {
            LOG.error("Redis异常:" + e);
        }
        if (page == null) {
            page = blogDao.findByBlogStatusIn(blogStatuses, pageable);
            //清楚内容，减小redis对象大小
            for (Blog blog : page.getContent()) {
                blog.setBlogContent(null);
            }
            try {
                valueOs.set("blog-page-" + pageable.getPageNumber() + "-" + pageable.getPageSize() + "-" + pageable.getSort(), page, 5, TimeUnit.MINUTES);
            } catch (Exception e) {
                LOG.error("Redis异常:" + e);
            }
        }
        return page;
    }

    public Page<Blog> findBlogPageByCatalogId(List<String> blogStatuses, Long catalogId, Pageable pageable) {
        Page<Blog> page = null;
        try {
            page = valueOs.get("blog-page-" + pageable.getPageNumber() + "-" + pageable.getPageSize() + "-" + pageable.getSort() + "-" + catalogId);
        } catch (Exception e) {
            LOG.error("Redis异常:" + e);
        }
        if (page == null) {
            page = blogDao.findBlogPageByCatalogId(blogStatuses, catalogId, pageable);
            //清楚内容，减小redis对象大小
            for (Blog blog : page.getContent()) {
                blog.setBlogContent(null);
            }
            try {
                valueOs.set("blog-page-" + pageable.getPageNumber() + "-" + pageable.getPageSize() + "-" + pageable.getSort() + "-" + catalogId, page, 5, TimeUnit.MINUTES);
            } catch (Exception e) {
                LOG.error("Redis异常:" + e);
            }
        }
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
        if (!BlogStatusEnum.NORMAL.getKey().equals(blog.getBlogStatus())) {
            throw new Exception(blog.getBlogId() + " " + blog.getBlogTitle() + " " + "博客状态是" + BlogStatusEnum.getValue(blog.getBlogStatus()) + " 无法建立索引！");
        }
        BlogCore blogCore = BlogToBlogCore(blog);
        return solrBlogBean.addIndex(blogCore);
    }

    private BlogCore BlogToBlogCore(Blog blog) {
        BlogCore blogCore = new BlogCore();
        blogCore.setBlogId(blog.getBlogId());
        blogCore.setBlogTitle(blog.getBlogTitle());
        blogCore.setBlogBrief(blog.getBlogBrief());
        blogCore.setBlogContent(HtmlUtil.deleteAllHTMLTag(blog.getBlogContent()));
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
        return blogCore;
    }

    public int indexDelete(Long blogId) throws Exception {
        return solrBlogBean.deleteByQuery(blogId);
    }

    public Page<Blog> queryBlogByKeyWords(String keywords, Pageable pageable) throws Exception {
        Page<Blog> page = solrBlogQuery.queryByKeyWords(keywords, pageable);
        return page;
    }

    public synchronized int indexCreateAll() throws Exception {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(0, 10, sort);
        int res = 0;
        Page<Blog> page;
        do {
            page = findPageByBlogStatus(StatusesCommonUse.blogStatusesNormal, pageable);
            List<BlogCore> blogCores = new ArrayList<>();
            for (Blog blog : page.getContent()) {
                BlogCore blogCore = BlogToBlogCore(blog);
                blogCores.add(blogCore);
            }
            res = solrBlogBean.addIndexList(blogCores);
            LOG.info("索引10条建立完毕~准备接下来来10条");
            pageable = new PageRequest(pageable.getPageNumber() + 1, 10, sort);
        } while (page.hasNext());

        return res;
    }

    public int indexDeleteAll() throws Exception {
        return solrBlogBean.deleteAll();
    }
}
