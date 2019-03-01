package tk.mybatis.simple.util;

/**
 * @Auther: kean_qi
 * @Date: 2019/3/1 10:20
 * @Description:
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        return  str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str){
        return  !isEmpty(str);
    }
}
