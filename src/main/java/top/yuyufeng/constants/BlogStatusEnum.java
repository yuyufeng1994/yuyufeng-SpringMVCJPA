package top.yuyufeng.constants;

import org.springframework.util.StringUtils;

/**
 * Created by yuyufeng on 2017/8/7.
 */
public enum BlogStatusEnum {
    NORMAL("0", "正常"), PRIVATE("1", "私有"), LOCK("2", "冻结"), REVIEWING("3", "审核中"), DELETED("4", "已删除");
    private String key;
    private String value;

    BlogStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        for (BlogStatusEnum blogStatusEnum : BlogStatusEnum.values()) {
            if (blogStatusEnum.getKey().equals(key)) {
                return blogStatusEnum.value;
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