package top.yuyufeng.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyufeng on 2017/8/8.
 */
public class StatusesCommonUse {
    //只获取正常状态的博客列表
    public static List<String> blogStatusesNormal = new ArrayList<>();
    //管理员的帐号类型集合
    public static List<String> accountTypesManager = new ArrayList<>();

    static {
        blogStatusesNormal.add(BlogStatusEnum.NORMAL.getKey());

        accountTypesManager.add(UserAccountTypeEnum.SUPER_MANAGER.getKey());
        accountTypesManager.add(UserAccountTypeEnum.MAMAGER.getKey());
    }
}
