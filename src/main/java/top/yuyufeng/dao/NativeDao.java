package top.yuyufeng.dao;

import org.springframework.stereotype.Repository;
import top.yuyufeng.dto.CataLogDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyufeng on 2017/8/3.
 */
@Repository
public class NativeDao {
    @PersistenceContext//(unitName="")
    private EntityManager em;
    public List<CataLogDto> findAllCataLogs(){
        Query query = em.createNativeQuery("SELECT catalogId,catalogName,catalogBrief,(SELECT count(blogId) FROM blog_catalog_info WHERE catalogId = catalog_info.catalogId) as blogsSize FROM catalog_info");
        List<Object[]> list= query.getResultList();
        List<CataLogDto> cataLogDtos = new ArrayList<>();
        for (Object[] objects : list) {
            CataLogDto cataLogDto = new CataLogDto();
            BigInteger catalogId = (BigInteger) objects[0];
            BigInteger blogsSize = (BigInteger) objects[3];
            cataLogDto.setCatalogId(catalogId.longValue());
            cataLogDto.setCatalogName((String) objects[1]);
            cataLogDto.setCatalogBrief((String) objects[2]);
            cataLogDto.setBlogsSize(blogsSize.intValue());
            cataLogDtos.add(cataLogDto);
        }
        return cataLogDtos;
    }
}
