package top.yuyufeng.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.yuyufeng.entity.User;
import top.yuyufeng.exception.AuthorityException;
import top.yuyufeng.service.UserService;
import top.yuyufeng.utils.SessionUserUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SessionUserUtil sessionUserUtil;

    @Resource(name = "urlMap")
    private Map<String, String> urlMap;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String tologin() {
        return "admin/login";
    }

    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    public String tologin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        sessionUserUtil.deleteSessionUser(httpServletRequest, httpServletResponse);
        return "admin/login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(User user, String returnUrl, HttpServletResponse httpServletResponse) throws AuthorityException, UnsupportedEncodingException {
        System.out.println(user);
        User record;
        record = userService.doCheckAccount(user);
        sessionUserUtil.setSessionUser(httpServletResponse, record);
        LOG.info(record.getUserAccount() + " 登录成功!");
        if (StringUtils.isEmpty(returnUrl)) {
            return "redirect:" + urlMap.get("appServer") + "/admin/index";
        }
        return "redirect:" + returnUrl;
    }
}
