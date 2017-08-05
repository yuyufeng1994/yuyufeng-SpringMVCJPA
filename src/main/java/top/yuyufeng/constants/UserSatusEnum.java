package top.yuyufeng.constants;

import org.springframework.util.StringUtils;

/**
 * 用户帐号状态
 * Created by yuyufeng on 2017/6/23.
 */
public enum UserSatusEnum {
    NORMAL("0", "正常"), LOCK("1", "冻结"), REVIEWING("2", "审核中");
    private String key;
    private String value;

    UserSatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        for (UserSatusEnum userSatusEnum : UserSatusEnum.values()) {
            if (userSatusEnum.getKey().equals(key)) {
                return userSatusEnum.value;
            }
        }
        return "unknow";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
