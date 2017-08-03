package top.yuyufeng.service;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.yuyufeng.dao.UserDao;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.User;
import top.yuyufeng.exception.AuthorityException;
import top.yuyufeng.utils.MD5Util;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by yuyufeng on 2017/8/2.
 */
@Service
public class UserService extends BaseServiceAbstract<User> {
    private final static String salt = "yuyufeng";

    @Override
    public User findOneById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public User save(User entity) {
        return userDao.save(entity);
    }

    @Override
    public void deleteOne(Long id) {
        userDao.delete(id);
    }

    @Override
    public Page<User> findPage(Pageable pageable) {
        if (pageable == null) {
            List<User> list = IteratorUtils.toList(userDao.findAll().iterator());
            Page<User> page = new PageImpl<User>(list);
            return page;
        }
        return userDao.findAll(pageable);
    }

    /**
     * 判断用户是否登录
     * @param user
     * @return
     */
    public User doCheckAccount(User user) throws AuthorityException, UnsupportedEncodingException {
        if(user == null|| StringUtils.isEmpty(user.getUserAccount())){
            throw new AuthorityException("输入信息不完整");
        }
        User record = userDao.findByUserAccount(user.getUserAccount());
        if (record == null) {
            throw new AuthorityException("用户不存在");
        }
        if (!MD5Util.getMd5(salt+user.getUserPassword()).equals(record.getUserPassword())) {
            throw new AuthorityException("用户密码不正确");
        }
        return record;
    }
}
