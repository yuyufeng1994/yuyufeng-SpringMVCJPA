/**
 * Created by yuyufeng on 2017/8/2.
 */
/**
 * 计算页码
 */
function CalcNavigatepageNums(pageNum,pages) {
    var navigatePages = 10;
    this.navigatepageNums;
    var startNum;
    if (pages <= navigatePages) {
        this.navigatepageNums = new Array(pages);

        for (startNum = 0; startNum < pages; ++startNum) {
            this.navigatepageNums[startNum] = startNum + 1;
        }
    } else {
        this.navigatepageNums = new Array(navigatePages);
        startNum = pageNum - navigatePages / 2;
        var endNum = pageNum + navigatePages / 2;
        var i;
        if (startNum < 1) {
            startNum = 1;

            for (i = 0; i < navigatePages; ++i) {
                this.navigatepageNums[i] = startNum++;
            }
        } else if (endNum > pages) {
            endNum = pages;
            for (i = navigatePages - 1; i >= 0; --i) {
                this.navigatepageNums[i] = endNum--;
            }
        } else {
            for (i = 0; i < navigatePages; ++i) {
                this.navigatepageNums[i] = startNum++;
            }
        }

    }
    return this.navigatepageNums;
}