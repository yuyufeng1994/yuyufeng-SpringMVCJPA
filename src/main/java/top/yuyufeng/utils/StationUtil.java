package top.yuyufeng.utils;


import top.yuyufeng.vo.StationVo;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yuyufeng on 2017/9/1.
 */
public class StationUtil {
    private static Map<String, StationVo> stationVoTreeMap = new TreeMap<String, StationVo>();

    static {
        File file = new File(StationUtil.class.getResource("/data/station_name.js").getFile());
        InputStreamReader read = null;
        try {
            read = new InputStreamReader(new FileInputStream(file),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bReader = new BufferedReader(read);
        String string = null;
        try {
            string = bReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = string.split("\\|");
        arr[0] = arr[0].substring(arr[0].indexOf("='") + 2);
        arr[arr.length - 1] = arr[arr.length - 1].substring(0, arr[arr.length - 1].indexOf("';"));
        List<StationVo> stationVos = new LinkedList<StationVo>();
        StationVo stationVo = new StationVo();
        for (int i = 0; i < arr.length; i++) {
            int flag = i % 5;
            switch (flag) {
                case 0:
                    stationVo.setStationId(arr[i]);
                    break;
                case 1:
                    stationVo.setStationName(arr[i]);
                    break;
                case 2:
                    stationVo.setStationCode(arr[i]);
                    break;
                case 3:
                    stationVo.setStationPinYin(arr[i]);
                    break;
                case 4: {
                    stationVo.setStationNameInitial(arr[i]);
                    stationVos.add(stationVo);
                    stationVo = new StationVo();
                    break;
                }
            }
        }


        for (StationVo vo : stationVos) {
            stationVoTreeMap.put(vo.getStationName(), vo);
        }

    }

    public static StationVo getStationByStationName(String stationName) {
        return stationVoTreeMap.get(stationName);
    }

    public static void main(String[] args) {
        System.out.println(getStationByStationName("嘉兴"));
    }

}
