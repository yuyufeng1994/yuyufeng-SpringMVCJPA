package top.yuyufeng.utils;

import java.util.Arrays;

/**
 * Created by yuyufeng on 2017/8/2.
 */
public class PageUtil {
    public static int[] calcNavigatepageNums(int pageNum, int pages) {
        int navigatePages = 10;
        int[] navigatepageNums;
        int startNum;
        if (pages <= navigatePages) {
            navigatepageNums = new int[pages];
            for (startNum = 0; startNum < pages; ++startNum) {
                navigatepageNums[startNum] = startNum + 1;
            }
        } else {
            navigatepageNums = new int[navigatePages];
            startNum = pageNum - navigatePages / 2;
            int endNum = pageNum + navigatePages / 2;
            int i;
            if (startNum < 1) {
                startNum = 1;

                for (i = 0; i < navigatePages; ++i) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > pages) {
                endNum = pages;
                for (i = navigatePages - 1; i >= 0; --i) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                for (i = 0; i < navigatePages; ++i) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
        return navigatepageNums;
    }
}
