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
import top.yuyufeng.entity.User;
import top.yuyufeng.service.UserService;
import top.yuyufeng.utils.PageUtil;

/**
 * Created by yuyufeng on 2017/8/1.
 */

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list/{pageNo}", method = RequestMethod.GET)
    public String toList(Model model, @PathVariable("pageNo") Integer pageNo) {
        pageNo = pageNo < 1 ? 1 : pageNo;
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(--pageNo, 10, sort);
        Page<User> page = userService.findPage(pageable);
        model.addAttribute("page", page);
        model.addAttribute("pages", PageUtil.calcNavigatepageNums(++pageNo, page.getTotalPages()));
        return "admin/user/list";
    }

    @RequestMapping(value = "/develop", method = RequestMethod.GET)
    public String toDevelop() {
        return "admin/develop";
    }

}
