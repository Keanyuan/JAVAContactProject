package com.anji.plus.study.javapro;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: Kean
 * @Date: 2019/2/24 8:17 PM
 * @Description:
 */
public class DateDemo {

    public static void main(String[] args) {
        dateCalendar();
        timeStampToDate();
    }


    // 时间戳转换成时间
    private static void timeStampToDate() {
        Long timeStamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 时间戳转换成时间
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        System.out.println("时间戳转 " + timeStamp + " 换成时间: " + sd);
    }

    //格式化时间
    private static void simpleDataFormat() {
        Date date = new Date();
        String strDateFormate = "yyyy-MM-dd HH:mm:ss a";
//        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormate);
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(strDateFormate);
        System.out.println(sdf.format(date));
    }

    //获取年份、月份等
    private static void dateCalendar() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        dow = getDayAndWeek(dow);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int doy = cal.get(Calendar.DAY_OF_YEAR);

        System.out.println("当期时间: " + cal.getTime());
        System.out.println("日期: " + day);
        System.out.println("月份: " + month);
        System.out.println("年份: " + year);
        System.out.println("一周的第几天: " + dow);  // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
        System.out.println("一月中的第几天: " + dom);
        System.out.println("一年的第几天: " + doy);
    }

    public static int getDayAndWeek(int day) {
        int week = 1;
        if (day == 1) {
            week = 7;
        } else {
            week = day - 1;
        }
        return week;
    }


}
