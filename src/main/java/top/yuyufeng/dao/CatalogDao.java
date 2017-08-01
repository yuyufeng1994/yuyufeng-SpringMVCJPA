package top.yuyufeng.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.entity.User;

/**
 * Created by yuyufeng on 2017/8/1.
 */
public interface CatalogDao extends PagingAndSortingRepository<Catalog, Long> {
}
