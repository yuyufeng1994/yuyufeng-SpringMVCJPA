package top.yuyufeng.service;

import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import top.yuyufeng.dto.CataLogDto;
import top.yuyufeng.entity.Catalog;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuyufeng on 2017/8/2.
 */
@Service
public class CatalogService extends BaseServiceAbstract<Catalog> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "redisTemplate")
    private ValueOperations<String, List<CataLogDto>> valueOs;

    @Override
    public Catalog findOneById(Long id) {
        return catalogDao.findOne(id);
    }

    @Override
    public Catalog save(Catalog entity) {
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

    public List<CataLogDto> findAllCatalogs(List<String> blogStatusesNormal) {
        List<CataLogDto> list = null;
        try {
            list = valueOs.get("catalogs");   //增加redis缓存
        } catch (Exception e) {
            LOG.error("Redis异常:" + e);
        }
        if (list == null || list.size() == 0) {
            list = nativeDao.findAllCataLogs(blogStatusesNormal);
            try {
                valueOs.set("catalogs", list, 10, TimeUnit.MINUTES);
            } catch (Exception e) {
                LOG.error("Redis异常:" + e);
            }

        }
        return list;
    }
}
