package top.yuyufeng.service;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.yuyufeng.dao.UserDao;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.User;

import java.util.List;

/**
 * Created by yuyufeng on 2017/8/2.
 */
@Service
public class UserService extends BaseServiceAbstract<User> {

    @Override
    public User findOneById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public User Save(User entity) {
        return userDao.save(entity);
    }

    @Override
    public void deleteOne(Long id) {
        userDao.delete(id);
    }

    @Override
    public Page<User> findPage(Pageable pageable) {
        if(pageable  == null){
            List<User> list =  IteratorUtils.toList(userDao.findAll().iterator());
            Page<User> page = new PageImpl<User>(list);
            return page;
        }
        return userDao.findAll(pageable);
    }
}
