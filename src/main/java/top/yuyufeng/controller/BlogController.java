package top.yuyufeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String toIndex(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(0, 10, sort);
        Page<Blog> page = blogService.findPage(pageable);
        model.addAttribute("page",page);
        return "blog/index";
    }

    @RequestMapping(value = "/content/{blogId}", method = RequestMethod.GET)
    public String toPost(Model model,@PathVariable("blogId") Long blogId) {
        Blog blog = blogService.findOneById(blogId);
        model.addAttribute("blog",blog);
        return "blog/content";
    }
}
