package top.yuyufeng.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuyufeng on 2017/6/7.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Resource(name = "propertiesMap")
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

    @RequestMapping(value = "/stream/decode", method = RequestMethod.GET)
    public void decodeQR(String content, HttpServletResponse response) throws IOException, WriterException {
        OutputStream os = response.getOutputStream();
        int width = 200; // 图像宽度
        int height = 200; // 图像高度
        String format = "png";// 图像类型
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
        MatrixToImageWriter.writeToStream(bitMatrix, format, os);
    }
}
