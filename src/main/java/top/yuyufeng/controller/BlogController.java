package top.yuyufeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.service.BlogService;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex() {
        Page<Blog> page = blogService.findPage(null);
        System.out.println(page);
        return "blog/index";
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String toPost() {
        return "blog/post";
    }
}
