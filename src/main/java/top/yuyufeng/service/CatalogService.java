package top.yuyufeng.service;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.yuyufeng.dao.CatalogDao;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.entity.User;

import java.util.List;

/**
 * Created by yuyufeng on 2017/8/2.
 */
@Service
public class CatalogService extends BaseServiceAbstract<Catalog> {

    @Override
    public Catalog findOneById(Long id) {
        return catalogDao.findOne(id);
    }

    @Override
    public Catalog Save(Catalog entity) {
        return catalogDao.save(entity);
    }

    @Override
    public void deleteOne(Long id) {
        catalogDao.delete(id);
    }

    @Override
    public Page<Catalog> findPage(Pageable pageable) {
        if (pageable == null) {
            List<Catalog> list = IteratorUtils.toList(catalogDao.findAll().iterator());
            Page<Catalog> page = new PageImpl<Catalog>(list);
            return page;
        }
        return catalogDao.findAll(pageable);
    }
}
