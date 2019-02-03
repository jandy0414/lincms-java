package cn.chenxins.utils;

public class StringUtil {

    public static boolean isNotBlank(String str) {
        if (str==null || "".equals(str) || str.isEmpty()){
            return false;
        }
        return true;
    }


}
