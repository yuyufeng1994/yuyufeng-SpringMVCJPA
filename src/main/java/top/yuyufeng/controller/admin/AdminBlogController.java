package top.yuyufeng.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList() {
        return "admin/blog/list";
    }

}
