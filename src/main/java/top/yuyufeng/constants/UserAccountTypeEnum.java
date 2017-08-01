package top.yuyufeng.constants;

import org.springframework.util.StringUtils;

/**
 * Created by yuyufeng on 2017/8/1.
 */
public enum UserAccountTypeEnum {
    SUPER_MANAGER("1", "超级管理员"), COMMON_USER("2", "普通用户");
    private String key;
    private String value;

    UserAccountTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        for (UserAccountTypeEnum userAccountTypeEnum : UserAccountTypeEnum.values()) {
            if (userAccountTypeEnum.getKey().equals(key)) {
                return userAccountTypeEnum.value;
            }
        }
        return "";
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
