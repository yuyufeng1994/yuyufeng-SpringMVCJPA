package top.yuyufeng.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yuyufeng on 2017/8/1.
 */

@Controller
@RequestMapping("/admin")
public class AdminIndexController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex() {
        return "admin/index";
    }
    @RequestMapping(value = "/develop", method = RequestMethod.GET)
    public String toDevelop() {
        return "admin/develop";
    }

}
