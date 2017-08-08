package top.yuyufeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Controller
public class IndexController {
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex() {
        return "index";
    }

    /**
     * 跳转到"开发中"页面
     * @return
     */
    @RequestMapping(value = "/develop", method = RequestMethod.GET)
    public String toDevelop() {
        return "develop";
    }

}