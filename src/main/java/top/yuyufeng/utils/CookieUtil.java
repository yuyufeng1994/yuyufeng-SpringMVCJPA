package top.yuyufeng.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void setCookie(HttpServletResponse httpServletResponse, String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        httpServletResponse.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest httpServletRequest, String cookieName) {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookieName.equals(cookies[i].getName())) {
                return cookies[i].getValue();
            }
        }
        return null;
    }

    public static void clearCookie(HttpServletResponse httpServletResponse, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        httpServletResponse.addCookie(cookie);
    }

}
