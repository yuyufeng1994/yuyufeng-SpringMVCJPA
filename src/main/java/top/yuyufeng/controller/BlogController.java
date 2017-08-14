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
import top.yuyufeng.constants.BlogStatusEnum;
import top.yuyufeng.constants.StatusesCommonUse;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.exception.BlogException;
import top.yuyufeng.service.BlogService;
import top.yuyufeng.service.CatalogService;
import top.yuyufeng.solr.blog.SolrBlogQuery;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    SolrBlogQuery solrBlogQuery;

    @Resource(name = "urlMap")
    private Map<String, String> urlMap;

    /**
     * 博客主页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex(Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(0, 10, sort);
        Page<Blog> page = blogService.findPageByBlogStatus(StatusesCommonUse.blogStatusesNormal, pageable);
        model.addAttribute("page", page);
        model.addAttribute("pagerUrl", urlMap.get("appServer") + "/blog/list");
        return "blog/list";
    }

    /**
     * 博客分页列表
     *
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/list/{pageNo}", method = RequestMethod.GET)
    public String toList(Model model, @PathVariable("pageNo") Integer pageNo) {
        pageNo = pageNo < 1 ? 1 : pageNo;
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(--pageNo, 10, sort);
        Page<Blog> page = blogService.findPageByBlogStatus(StatusesCommonUse.blogStatusesNormal, pageable);
        model.addAttribute("page", page);
        model.addAttribute("pagerUrl", urlMap.get("appServer") + "/blog/list");
        return "blog/list";
    }

    /**
     * 博客搜索分页列表
     *
     * @param model
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/search/{pageNo}", method = RequestMethod.GET)
    public String toSearch(Model model, @PathVariable("pageNo") Integer pageNo, String keywords) throws Exception {
        pageNo = pageNo < 1 ? 1 : pageNo;
        Pageable pageable = new PageRequest(--pageNo, 10);
        Page<Blog> page = blogService.queryBlogByKeyWords(keywords,pageable);

        model.addAttribute("page", page);
        model.addAttribute("pagerUrl", urlMap.get("appServer") + "/blog/search");
        model.addAttribute("extraParam", "?keywords=" + keywords);
        model.addAttribute("keywords", keywords);
        return "blog/list";
    }


    /**
     * 博客主体页面
     *
     * @param model
     * @param blogId
     * @return
     * @throws BlogException
     */
    @RequestMapping(value = "/content/{blogId}", method = RequestMethod.GET)
    public String toContent(Model model, @PathVariable("blogId") Long blogId) throws BlogException {
        Blog blog = blogService.findOneById(blogId);
        if (blog == null) {
            throw new BlogException("您要访问的博客不存在!");
        }
        model.addAttribute("blog", blog);
        return "blog/content";
    }

    /**
     * 博客分类检索
     *
     * @param model
     * @param catalogId
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/list-catalog/{catalogId}/{pageNo}", method = RequestMethod.GET)
    public String toListByCatalog(Model model, @PathVariable("catalogId") Long catalogId, @PathVariable("pageNo") Integer pageNo) {
        pageNo = pageNo < 1 ? 1 : pageNo;
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(--pageNo, 10, sort);
        Page<Blog> page = blogService.findBlogPageByCatalogId(StatusesCommonUse.blogStatusesNormal, catalogId, pageable);
        Catalog catalog = catalogService.findOneById(catalogId);
        model.addAttribute("page", page);
        model.addAttribute("catalog", catalog);
        model.addAttribute("pagerUrl", urlMap.get("appServer") + "/blog/list-catalog/"+catalogId);
        return "blog/list";
    }
}
