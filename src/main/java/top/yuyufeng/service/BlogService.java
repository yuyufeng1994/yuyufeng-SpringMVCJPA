package top.yuyufeng.service;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.yuyufeng.dao.BlogDao;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.User;

import java.util.List;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Service
public class BlogService extends BaseServiceAbstract<Blog> {

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


    public Page<Blog> findBlogPageByCatalogId(Long catalogId, Pageable pageable) {
        Page<Blog> page = blogDao.findBlogPageByCatalogId(catalogId, pageable);
        return page;
    }
}
