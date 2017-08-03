package top.yuyufeng.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.yuyufeng.entity.User;

/**
 * Created by yuyufeng on 2017/8/1.
 */
public interface UserDao extends PagingAndSortingRepository<User, Long> {
    User findByUserAccount(String userAccount);
}
