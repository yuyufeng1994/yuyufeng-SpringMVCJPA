package dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import top.yuyufeng.dao.CatalogDao;
import top.yuyufeng.entity.Catalog;

/**
 * Created by yuyufeng on 2017/8/1.
 */
public class CatalogDaoTest extends BaseDaoTest {
    @Autowired
    private CatalogDao catalogDao;

    @Test
    public void test() {
    }

    @Test
    public void testInsert() {
        for (int i = 0; i < 10; i++) {
            Catalog catalog = new Catalog();
            catalog.setCatalogName("分类" + i);
            catalog.setCatalogBrief("分类" + i + "系列");
            catalogDao.save(catalog);
        }
    }

    @Test
    public void testQuery() {
        Sort sort = new Sort(Sort.Direction.DESC, "catalogId");
        Pageable pageable = new PageRequest(0, 10, sort);
        Page<Catalog> page  = catalogDao.findAll(pageable);
        for (Catalog catalog : page.getContent()) {
            System.out.println(catalog.getCatalogName());
        }
    }



}
