import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yuyufeng on 2017/8/2.
 */
public class MainTest {
    public static void main(String[] args) {
        String str="aa,bb,cc,";
        String st[] = str.split(",");
        for (String s : st) {
            System.out.println("* "+s);
        }
    }

}
