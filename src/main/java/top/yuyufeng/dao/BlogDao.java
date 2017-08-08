package top.yuyufeng.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.User;

import java.util.List;

/**
 * Created by yuyufeng on 2017/8/1.
 */
public interface BlogDao extends PagingAndSortingRepository<Blog, Long> {

    /**
     * 根据博客目录查出博客分页数据
     *
     * @param catalogId
     * @param pageable
     * @return
     */
//    @Query(value = "SELECT * FROM blog_info WHERE blogId IN (SELECT blogId FROM blog_catalog_info WHERE catalogId = ?1) order by ?#{#pageable}",countQuery = "SELECT blogId,blogTitle FROM blog_info WHERE blogId IN (SELECT blogId FROM blog_catalog_info WHERE catalogId = ?1)",nativeQuery = true)
    @Query("select b from Blog b,Catalog c where b.blogStatus in (?1) and c.catalogId=?2 and b.blogId in elements(c.blogs)")
    Page<Blog> findBlogPageByCatalogId(List<String> blogStatuses, Long catalogId, Pageable pageable);


    /**
     * 根据博客状态查询博客分页数据
     *
     * @param blogStatuses
     * @param pageable
     * @return
     */
    Page<Blog> findByBlogStatusIn(List<String> blogStatuses, Pageable pageable);
}
