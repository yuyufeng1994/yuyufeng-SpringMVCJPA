package top.yuyufeng.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yuyufeng.constants.BlogStatusEnum;
import top.yuyufeng.entity.Blog;
import top.yuyufeng.entity.Catalog;
import top.yuyufeng.service.BlogService;
import top.yuyufeng.service.CatalogService;
import top.yuyufeng.solr.blog.SolrBlogBean;
import top.yuyufeng.utils.PageUtil;
import top.yuyufeng.utils.SessionUserUtil;
import top.yuyufeng.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {
    @Autowired
    private SessionUserUtil sessionUserUtil;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CatalogService catalogService;


    @RequestMapping(value = "/list/{pageNo}", method = RequestMethod.GET)
    public String toList(Model model, @PathVariable("pageNo") Integer pageNo) {
        pageNo = pageNo < 1 ? 1 : pageNo;
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(--pageNo, 10, sort);
        Page<Blog> page = blogService.findPage(pageable);
        model.addAttribute("page", page);
        model.addAttribute("pages", PageUtil.calcNavigatepageNums(++pageNo, page.getTotalPages()));
        return "admin/blog/list";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String toSave(Model model, Long blogId, String returnUrl) {
        List<Catalog> catalogs = catalogService.findPage(null).getContent();
        if (!StringUtils.isEmpty(blogId)) {
            Blog blog = blogService.findOneById(blogId);
            model.addAttribute("blog", blog);
            for (int i = 0; i < catalogs.size(); i++) {
                for (Catalog catalog : blog.getCatalogs()) {
                    if (catalogs.get(i).getCatalogId().equals(catalog.getCatalogId())) {
                        catalogs.get(i).setChecked("checked='checked'");
                    }
                }

            }
        }
        model.addAttribute("catalogs", catalogs);
        model.addAttribute("returnUrl", returnUrl);
        return "admin/blog/save";
    }

    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public String doSave(Model model, Blog blog, String returnUrl, Long[] catalogIds, HttpServletRequest request) {
        Set<Catalog> catalogs = new HashSet<>();
        if (catalogIds != null) {
            for (int i = 0; i < catalogIds.length; i++) {
                Catalog e = new Catalog();
                e.setCatalogId(catalogIds[i]);
                catalogs.add(e);
            }
        }
        blog.setCatalogs(catalogs);
        if (StringUtils.isEmpty(blog.getBlogId())) {
            blog.setCreateTime(new Date());
            blog.setBlogUser(sessionUserUtil.getSessionUser(request));
        }
        blogService.save(blog);
        return "redirect:" + returnUrl;
    }

    @Transactional
    @RequestMapping(value = "/doDelete", method = RequestMethod.GET)
    public String doDelete(Long blogId, String returnUrl) throws Exception {
        //删除文章
        blogService.deleteOne(blogId);
        //删除索引
        blogService.indexDelete(blogId);
        return "redirect:" + returnUrl;
    }

    @RequestMapping(value = "/doIndexCreate/{blogId}", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult doIndexCreate(@PathVariable("blogId") Long blogId) throws Exception {
        int res = blogService.indexCreate(blogId);
        boolean success = 0 == res ? true : false;
        return new JsonResult(success);
    }

    @RequestMapping(value = "/doIndexDelete/{blogId}", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult doIndexDelete(@PathVariable("blogId") Long blogId) throws Exception {
        int res = blogService.indexDelete(blogId);
        boolean success = 0 == res ? true : false;
        return new JsonResult(success);
    }

    @RequestMapping(value = "/doIndexCreateAll", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult doIndexCreateAll() throws Exception {
        int res = blogService.indexCreateAll();
        boolean success = 0 == res ? true : false;
        return new JsonResult(success);
    }

    @RequestMapping(value = "/doIndexDeleteAll", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonResult doIndexDeleteAll() throws Exception {
        int res = blogService.indexDeleteAll();
        boolean success = 0 == res ? true : false;
        return new JsonResult(success);
    }


}
