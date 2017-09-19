package top.yuyufeng.vo;

/**
 * Created by yuyufeng on 2017/9/1.
 */
public class StationVo {
    private String stationId;
    private String stationName;
    private String stationCode;
    private String stationNameInitial;
    private String stationPinYin;

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationNameInitial() {
        return stationNameInitial;
    }

    public void setStationNameInitial(String stationNameInitial) {
        this.stationNameInitial = stationNameInitial;
    }

    public String getStationPinYin() {
        return stationPinYin;
    }

    public void setStationPinYin(String stationPinYin) {
        this.stationPinYin = stationPinYin;
    }

    public StationVo() {
    }

    @Override
    public String toString() {
        return "StationVo{" +
                "stationId='" + stationId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationCode='" + stationCode + '\'' +
                ", stationNameInitial='" + stationNameInitial + '\'' +
                ", stationPinYin='" + stationPinYin + '\'' +
                '}';
    }

}
