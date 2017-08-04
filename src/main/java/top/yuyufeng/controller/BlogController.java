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
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.exception.BlogException;
import top.yuyufeng.service.BlogService;
import top.yuyufeng.service.CatalogService;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(0, 10, sort);
        Page<Blog> page = blogService.findPage(pageable);
        model.addAttribute("page",page);
        return "blog/index";
    }

    @RequestMapping(value = "/list/{pageNo}", method = RequestMethod.GET)
    public String toList(Model model,@PathVariable("pageNo") Integer pageNo) {
        pageNo = pageNo < 1 ? 0 : pageNo;
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(--pageNo, 10, sort);
        Page<Blog> page = blogService.findPage(pageable);
        model.addAttribute("page",page);
        return "blog/index";
    }

    @RequestMapping(value = "/content/{blogId}", method = RequestMethod.GET)
    public String toContent(Model model,@PathVariable("blogId") Long blogId) throws BlogException {
        Blog blog = blogService.findOneById(blogId);
        if(blog == null){
            throw new BlogException("您要访问的博客不存在!");
        }
        model.addAttribute("blog",blog);
        return "blog/content";
    }

    @RequestMapping(value = "/list-catalog/{catalogId}/{pageNo}", method = RequestMethod.GET)
    public String toListByCatalog(Model model,@PathVariable("catalogId") Long catalogId,@PathVariable("pageNo") Integer pageNo) {
        pageNo = pageNo < 1 ? 0 : pageNo;
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(--pageNo, 10,sort);
        Page<Blog> page = blogService.findBlogPageByCatalogId(catalogId,pageable);
        Catalog catalog = catalogService.findOneById(catalogId);
        model.addAttribute("page", page);
        model.addAttribute("catalog", catalog);
        return "blog/list";
    }
}
