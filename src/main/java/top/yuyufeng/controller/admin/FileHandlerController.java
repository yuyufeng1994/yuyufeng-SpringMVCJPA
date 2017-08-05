package top.yuyufeng.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.yuyufeng.vo.UploadResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yuyufeng on 2017/6/7.
 */
@Controller
@RequestMapping("/admin/file")
public class FileHandlerController {

    @Resource(name = "propertiesMap")
    private Map<String, String> proMap;
    @Resource(name = "urlMap")
    private Map<String, String> urlMap;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    UploadResult doUpload(HttpServletRequest request) throws IOException {
        UploadResult ur = new UploadResult();
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        String[] strArr = new String[fileMap.size()];
        int i = 0;
        for (String fileName : fileMap.keySet()) {
            String uuid = UUID.randomUUID().toString();
            String ext = StringUtils.getFilenameExtension(fileName);
            MultipartFile file = fileMap.get(fileName);
            File getFile = new File(proMap.get("fileContent") + uuid + "." + ext);
            file.transferTo(getFile);
            strArr[i] = urlMap.get("fileServer") + "/common/stream/" + uuid + "/" + ext;
            i++;
        }
        ur.setErrno(0);
        ur.setData(strArr);
        return ur;
    }

}
