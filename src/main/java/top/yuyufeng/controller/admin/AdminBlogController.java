package top.yuyufeng.controller.admin;

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
import top.yuyufeng.utils.PageUtil;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/list/{pageNo}", method = RequestMethod.GET)
    public String toList(Model model, @PathVariable("pageNo") Integer pageNo) {
        pageNo = pageNo < 1 ? 0 : pageNo;
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(--pageNo, 10, sort);
        Page<Blog> page = blogService.findPage(pageable);
        model.addAttribute("page", page);
        model.addAttribute("pages", PageUtil.calcNavigatepageNums(++pageNo, page.getTotalPages()));
        return "admin/blog/list";
    }

    @RequestMapping(value = "/save/{blogId}", method = RequestMethod.GET)
    public String toSave(Model model, @PathVariable("blogId") Long blogId, String returnUrl) {
        Blog blog = blogService.findOneById(blogId);
        model.addAttribute("blog", blog);
        model.addAttribute("returnUrl", returnUrl);
        return "admin/blog/save";
    }

    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public String doSave(Model model, Blog blog, String returnUrl) {
        System.out.println(blog);
        return "redirect:" + returnUrl;
    }

}
