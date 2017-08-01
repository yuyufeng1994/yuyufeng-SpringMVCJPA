package top.yuyufeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;

/**
 * Created by yuyufeng on 2017/6/7.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Resource(name = "proMap")
    private Map<String, String> proMap;

    /**
     * 文件下载
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/download/{uuid}/{ext}", method = RequestMethod.GET)
    public String download(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                           @PathVariable("uuid") String uuid, @PathVariable("ext") String ext) throws IOException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + uuid);
        try {
            InputStream inputStream = new FileInputStream(proMap.get("fileContent") + uuid + "." + ext);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return null;
    }


    /**
     * 文件流
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/stream/{uuid}/{ext}", method = RequestMethod.GET)
    public String thumbnail(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                            @PathVariable("uuid") String uuid, @PathVariable("ext") String ext) {
        try {
            InputStream inputStream = new FileInputStream(proMap.get("fileContent") + uuid + "." + ext);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return null;
    }
}
