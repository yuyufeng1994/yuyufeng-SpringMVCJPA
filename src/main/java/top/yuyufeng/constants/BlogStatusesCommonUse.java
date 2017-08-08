package top.yuyufeng.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyufeng on 2017/8/8.
 */
public class BlogStatusesCommonUse {
    //只获取正常状态的博客列表
    public static List<String> blogStatusesNormal = new ArrayList<>();

    static {
        blogStatusesNormal.add(BlogStatusEnum.NORMAL.getKey());
    }
}
