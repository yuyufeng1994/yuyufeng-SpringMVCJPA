package top.yuyufeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex() {
        return "blog/index";
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String toPost() {
        return "blog/post";
    }
}
