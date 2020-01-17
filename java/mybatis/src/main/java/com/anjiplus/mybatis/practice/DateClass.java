package com.anjiplus.mybatis.practice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/21 10:52
 * @Description:
 */
public //日期
class DateClass {

    public static void testDate(){
        System.out.println("---------");
        System.out.println("三、总结\n" +
                "\n" +
                "Java中日期的经常有一下五个方面：\n" +
                "1、创建日期\n" +
                "2、日期格式化显示\n" +
                "3、日期的转换（主要是和字符串之间的相互转换）\n" +
                "4、日期中年、月、日、时、分、秒、星期、月份等获取。\n" +
                "5、日期的大小比较、日期的加减。");
        System.out.println("1.public Date()——分配 Date 对象并初始化此对象，以表示分配它的时间（精确到毫秒）。");
        Date date = new Date();
        System.out.println(date);

        System.out.println("2.public Date(long date)——根据给定的毫秒值创建日期对象。");
        long time = System.currentTimeMillis();
        Date date1 = new Date(time);
        System.out.println(date1);

        System.out.println("3.public long getTime()——日期转毫秒值");
        System.out.println(date.getTime());

        System.out.println("4.public void setTime(long time)——毫秒值转日期");
        date.setTime(time);
        System.out.println(date);

        System.out.println("1、public boolean before(Date when)——测试此日期是否在指定日期之前，\n当且仅当此Date对象表示的瞬间比when表示的瞬间早，才返回true；否则返回false.");
        Date date2 = new Date(1000);
        Date date3 = new Date(3000);
        System.out.println(date2.before(date3));
        System.out.println("2、public boolean after(Date when)——测试此日期是否在指定日期之后，\n当且仅当此Date对象表示的瞬间比when表示的瞬间晚，才返回true；否则返回false。");
        System.out.println(date2.after(date3));
        System.out.println("3、public int compareTo(Date anotherDate)——比较两个日期的顺序。");
        System.out.println("< " + date2.compareTo(date3));
        System.out.println("= " + date2.compareTo(date2));
        System.out.println("> " + date3.compareTo(date2));

        System.out.println("1、对SimpleDateFormat类进行测试");
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EE");
        //产生一个指定国家指定长度的日期格式，长度不同，显示的日期完整性也不同
        DateFormat dateFormat2 = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);
        DateFormat dateFormat3 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒", Locale.CHINA);
        DateFormat dateFormat4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss EEEEEE", Locale.US);
        DateFormat dateFormat5 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("-------将日期按照不同格式进行输出------");
        System.out.println("按照Java默认的日期格式，默认的区域                      : " + dateFormat.format(date));
        System.out.println("按照指定格式 yyyy-MM-dd hh:mm:ss EE ，系统默认区域      :" + dateFormat1.format(date));
        System.out.println("按照日期的FULL模式，区域设置为中文                      : " + dateFormat2.format(date));
        System.out.println("按照指定格式 yyyy年MM月dd日 hh时mm分ss秒 EE ，区域为中文 : " + dateFormat3.format(date));
        System.out.println("按照指定格式 yyyy-MM-dd hh:mm:ss EE ，区域为美国        : " + dateFormat4.format(date));
        System.out.println("按照指定格式 yyyy-MM-dd ，系统默认区域                  : " + dateFormat5.format(date));

        try {
            Date date4 = dateFormat.parse("16-01-24 下午2:32");
            Date date5 = dateFormat1.parse("2016-01-24 02:51:07 星期日");
            Date date6 = dateFormat2.parse("2016年01月24日 星期五");
            Date date7 = dateFormat3.parse("2016年01月24日 02时51分18秒 星期日");
            Date date8 = dateFormat4.parse("2016-01-24 02:51:18 Sunday");
            Date date9 = dateFormat5.parse("2016-01-24");

            System.out.println("-------输出将字符串转换为日期的结果------");
            System.out.println(date4);
            System.out.println(date5);
            System.out.println(date6);
            System.out.println(date7);
            System.out.println(date8);
            System.out.println(date9);


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void testCalendar(){
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar(2016, 01, 24);
        //陷阱:Calendar的月份是0~11
        Calendar calendar3 = new GregorianCalendar(2016,01, 24, 15, 55);
        Calendar calendar4 = new GregorianCalendar(2016, 01,24, 15, 55, 44);
        Calendar calendar5 = new GregorianCalendar(Locale.US);
        Calendar calendar6 = new GregorianCalendar(TimeZone.getTimeZone("GMT-8:00"));

        //通过日期和毫秒数设置Calendar
        calendar1.setTime(new Date());
        System.out.println(calendar1);

        calendar1.setTimeInMillis(new Date().getTime());
        System.out.println(calendar1);

        //定义日期的中文输出格式,并输出日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒 E", Locale.CHINA);
        System.out.println("获取日期中文格式化化输出：" + df.format(calendar1.getTime()));
        System.out.println("--------通过Calendar获取日期中年月日等相关信息--------");
        System.out.println("获取年：" + calendar1.get(Calendar.YEAR));
        System.out.println("获取月(月份是从0开始的)：" + calendar4.get(Calendar.MONTH));
        System.out.println("获取日：" + calendar1.get(Calendar.DAY_OF_MONTH));
        System.out.println("获取时：" + calendar1.get(Calendar.HOUR));
        System.out.println("获取分：" + calendar1.get(Calendar.MINUTE));
        System.out.println("获取秒：" + calendar1.get(Calendar.SECOND));
        System.out.println("获取上午、下午：" + calendar1.get(Calendar.AM_PM));
        System.out.println("获取星期数值(星期是从周日开始的)：" + calendar1.get(Calendar.DAY_OF_WEEK));
        System.out.println("---------通用星期中文化转换---------");
        String dayOfWeek[] = {"", "日", "一", "二", "三", "四", "五", "六"};
        System.out.println("today对象的星期是:" + dayOfWeek[calendar1.get(Calendar.DAY_OF_WEEK)]);
        System.out.println("---------通用月份中文化转换---------");
        String months[] = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        System.out.println("today对象的月份是: " + months[calendar1.get(Calendar.MONTH)]);
    }

    public static void testCalendar2(){
        //获取当前月份的最大天数
        Calendar calendar = Calendar.getInstance();
        int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int minday = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        System.out.println("获取当前月份的最大天数:" + maxday);
        System.out.println("获取当前月份的最小天数:" + minday);
        DateFormat format = new SimpleDateFormat("yyyy-MM-" + maxday);
        System.out.println("取当月的最后一天: " + format.format(calendar.getTime()));
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-" + minday);
        System.out.println("取当月的第一天: " + format1.format(calendar.getTime()));

        System.out.println("求两个日期之间相隔的天数");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date beginDate = format2.parse("2019-02-01");
            Date endDate = format2.parse("2019-02-22");
            long day = (endDate.getTime() - beginDate.getTime()) / (24*60*60*1000);
            System.out.println("相隔的天数 = " + day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("一年前的日期");
        Date todayDate = new Date();
        System.out.println(format2.format(todayDate));
        //方法1
        long beforeTime = (todayDate.getTime() / 1000) - 60*60*24*365;
        todayDate.setTime(beforeTime*1000);
        String beforeDate = format2.format(todayDate);
        System.out.println(beforeDate);
        //方法2
        calendar.add(Calendar.YEAR, -1);
        System.out.println(format2.format(calendar.getTime()));

        System.out.println("当前星期的星期一和星期日");
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int dayInWeek = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
        int offset = 0;
        if (dayInWeek == 1) {
            //星期天
            offset = 6;
        } else {
            //周一到周六
            offset = dayInWeek - 2;
        }
        System.out.println(offset);
        gregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, -offset);
        String sday = format2.format(gregorianCalendar.getTime());
        gregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, 6);
        String eday = format2.format(gregorianCalendar.getTime());
        System.out.println("这个星期的星期一:" + sday);
        System.out.println("这个星期的星期天:" + eday);
    }

}
