package top.yuyufeng.utils;

import top.yuyufeng.constants.SessionConstant;
import top.yuyufeng.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuyufeng on 2017/8/2.
 */
public class SessionUtil {
    public static User getSessionUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER);
        return user;
    }

    public static void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(SessionConstant.SESSION_USER, user);
    }
}
