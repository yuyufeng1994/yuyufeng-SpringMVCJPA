package top.yuyufeng.service.extra;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import top.yuyufeng.utils.JsoupUtil;
import top.yuyufeng.utils.StationUtil;


@Service
public class TrainService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        String fromStation = StationUtil.getStationByStationName("上海").getStationCode();
        String toStation = StationUtil.getStationByStationName("广州").getStationCode();
        String trainDate = "2017-10-01";
        try {
            new TrainService().queryTrainList(fromStation, toStation, trainDate, "ADULT");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> queryTrainList(String fromStation, String toStation, String trainDate, String purposeCode) throws IOException {
        List<String> returnList = new ArrayList<>();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8");
        headers.put("Cache-Control", "max-age=0");
        headers.put("Connection", "keep-alive");
        headers.put("If-Modified-Since", "0");
        headers.put("Host", "kyfw.12306.cn");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
//        headers.put("X-Forwarded-For", "115.236.91.20");
        JsoupUtil.trustEveryone();
        Document doc;
        Map<String, String> cookies;
        String result = "";
        Connection conn = Jsoup.connect("https://kyfw.12306.cn/otn/leftTicket/init")
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        Connection.Response response = conn.execute();
        cookies = response.cookies();
        for (String s : cookies.keySet()) {
            System.out.println(s + " " + cookies.get(s));
        }
        String url;

        System.out.println("===========开始查询余票==========");
        url = "https://kyfw.12306.cn/otn/leftTicket/queryX?leftTicketDTO.train_date=" + trainDate + "&leftTicketDTO.from_station=" + fromStation + "&leftTicketDTO.to_station=" + toStation + "&purpose_codes=" + purposeCode;
        System.out.println(url);

        System.out.println("正在查询");
        try {
            doc = Jsoup.connect(url)
                    .userAgent("537.36 (KHTML, like Gecko) Chrome")
                    .followRedirects(false)
                    .method(Connection.Method.GET)
                    .cookies(cookies)
                    .ignoreContentType(true)
                    .headers(headers)
                    .get();
            System.out.println(doc);
            result = doc.body().html();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        if (StringUtils.isNotEmpty(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            //序号  车次 出发站/到达站 出发时间/达到时间 历时 商务座  特等座 一等座 二等座 高级软卧 软卧 硬卧 软座 硬座 无座
            // **|预订|55000K137305|K1373|SNH|HHQ|JXH|DIQ|17:56|12:58|19:02|Y|**|20170920|3|H2|03|17|0|0||||1|||无||有|无|||||10401030|1413
            JSONArray resultArray = jsonObject.getJSONObject("data").getJSONArray("result");
            for (int i = 0; i < resultArray.size(); i++) {
                String resultString = resultArray.get(i).toString();
                String[] resultStringArray = resultString.split("\\|");

                for (int i1 = 0; i1 < resultStringArray.length; i1++) {
                    System.out.print("[" + i1 + "]" + resultStringArray[i1] + " ");
                    returnList.add("[" + i1 + "]" + resultStringArray[i1]);
                }
                System.out.println();
                System.out.println("===================================================");
            }
        }
        return returnList;
    }
}
