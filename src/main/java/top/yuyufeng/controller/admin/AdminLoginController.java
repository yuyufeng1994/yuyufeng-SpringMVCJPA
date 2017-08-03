package top.yuyufeng.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.yuyufeng.entity.User;
import top.yuyufeng.exception.AuthorityException;
import top.yuyufeng.service.UserService;
import top.yuyufeng.utils.SessionUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    @Resource(name = "urlMap")
    private Map<String, String> urlMap;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String tologin() {
        return "admin/login";
    }

    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    public String tologin(HttpServletRequest request) {
        request.getSession().invalidate();
        return "admin/login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(User user, String returnUrl, HttpServletRequest request) throws AuthorityException, UnsupportedEncodingException {
        System.out.println(user);
        User record;
        record = userService.doCheckAccount(user);
        SessionUtil.setSessionUser(request, record);
        if (StringUtils.isEmpty(returnUrl)) {
            return "redirect:" + urlMap.get("appServer") + "/admin/index";
        }
        return "redirect:" +returnUrl;
    }
}
