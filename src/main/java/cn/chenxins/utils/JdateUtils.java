package cn.chenxins.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JdateUtils {


    public static int getCurrentTime(){
        Date date=new Date();
        long lTime=date.getTime()/1000;
        int cTime=new Long(lTime).intValue();
        return cTime;
//        Java中数据转换很常见，提供两种方法，不推荐强制转化类型，亲测无用！
//        第一种：int returnId=new Long(a).intValue();
//        第二种：int returnId=Integer.parseInt(String.valueOf(a));
    }

    public static Date getCurrentDate(){
        Date date=new Date();
        return date;
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDateGenFormat(Date date) {
        if (date==null)
            return "";
        String pattern="yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat df=new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 获取两个日期之间的秒数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTimeDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000);
    }
}
