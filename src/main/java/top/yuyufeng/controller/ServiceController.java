package top.yuyufeng.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yuyufeng.service.extra.TrainService;
import top.yuyufeng.utils.StationUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Controller
@RequestMapping("/service")
public class ServiceController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TrainService trainService;

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex() {
        return "service/index";
    }

    /**
     * 火车票服务
     *
     * @return
     */
    @RequestMapping(value = "/toTrain", method = RequestMethod.GET)
    public String toTrain() {
        return "service/train";
    }

    @RequestMapping(value = "/doTrainList", method = RequestMethod.GET)
    public @ResponseBody
    List<String> toTrainList(String beginStation, String endStation, String date) {
        try {
            String fromStation = StationUtil.getStationByStationName(beginStation).getStationCode();
            String toStation = StationUtil.getStationByStationName(endStation).getStationCode();
            return trainService.queryTrainList(fromStation, toStation, date, "ADULT");
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return null;
        }

    }


    /**
     * 二维码服务
     *
     * @return
     */
    @RequestMapping(value = "/toQrcode", method = RequestMethod.GET)
    public String toQrcode() {
        return "service/qrcode";
    }

    /**
     * 二维码
     *
     * @param content
     * @param response
     * @throws IOException
     * @throws WriterException
     */

    @RequestMapping(value = "/doQrcode", method = RequestMethod.GET)
    public void doQrcode(String content, HttpServletResponse response) throws IOException, WriterException {
        if (StringUtils.isEmpty(content)) {
            return;
        }
        if (content.length() > 200) {
            content = content.substring(0, 200);
        }
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


    /**
     * 二维码下载
     *
     * @param content
     * @param response
     * @throws IOException
     * @throws WriterException
     */

    @RequestMapping(value = "/doQrcodeDownload", method = RequestMethod.GET)
    public void doQrcodeDownload(String content, HttpServletResponse response) throws IOException, WriterException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=qrcode.png");
        if (StringUtils.isEmpty(content)) {
            return;
        }
        if (content.length() > 200) {
            content = content.substring(0, 200);
        }
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