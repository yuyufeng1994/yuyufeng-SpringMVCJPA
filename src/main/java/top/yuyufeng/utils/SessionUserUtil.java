package top.yuyufeng.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.yuyufeng.constants.SessionConstant;
import top.yuyufeng.dto.CataLogDto;
import top.yuyufeng.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuyufeng on 2017/8/2.
 */
public class SessionUserUtil {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "redisTemplate")
    private ValueOperations<String, User> valueOs;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    //采用单点登录
    public User getSessionUser(HttpServletRequest httpServletRequest) {
        String cookieValue = CookieUtil.getCookieValue(httpServletRequest, SessionConstant.SESSION_USER);
        if (cookieValue == null) {
            return null;
        }
        User user = null;
        try {
            user = valueOs.get("session-" + cookieValue);
        } catch (Exception e) {
            LOG.error("从redis获取用户失败 " + e);
        }
        return user;
    }

    public void setSessionUser(HttpServletResponse httpServletResponse, User user) {
        String uuid = UUIDUtil.getUUIDString();
        CookieUtil.setCookie(httpServletResponse, SessionConstant.SESSION_USER, uuid);
        valueOs.set("session-" + uuid, user, 30, TimeUnit.MINUTES);
    }

    public void deleteSessionUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String cookieValue = CookieUtil.getCookieValue(httpServletRequest, SessionConstant.SESSION_USER);
        if (!StringUtils.isEmpty(cookieValue)) {
            try {
                redisTemplate.delete("session-" + cookieValue);
            } catch (Exception e) {
                LOG.error("从redis删除用户失败 " + e);
            }
        }
        CookieUtil.clearCookie(httpServletResponse, SessionConstant.SESSION_USER);
    }

    /*public static User getSessionUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER);
        return user;
    }

    public static void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(SessionConstant.SESSION_USER, user);
    }*/
}
