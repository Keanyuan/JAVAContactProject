/**
 * anji-oms.com Inc.
 * Copyright (c) 2006-2016 All Rights Reserved.
 */
package com.anjiplus.mybatis.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Weeks;

/**
 * <p>
 * 时间操作工具类
 * </p>
 * @author kean_qi
 * @version $Id: DateUtil.java, v 0.1 2018年4月30日 下午4:25:30 kean_qi Exp $
 */
public final class DateUtil {

    public static final String TIMENOWPATTERN             = "yyyy-MM-dd HH:mm:ss";
    public static final String TIMENOWPATTERNNOMINUS      = "yyyyMMddHHmmss";
    public static final String TIMEBEGINPATTERN           = "yyyy-MM-dd 00:00:00";
    public static final String TIMEENDPATTERN             = "yyyy-MM-dd 23:59:59";
    public static final String DATEANDMINUSPATTERN        = "yyyy-MM-dd";
    public static final String DATENOMINUSPATTERN         = "yyyyMMdd";
    public static final String MONTHANDDATENOMINUSPATTERN = "MMdd";
    public static final String YEARPATTERN                = "yyyy";
    public static final String TIMENODATEPATTERN          = "HH:mm:ss";
    public static final String TRANSTIMEPATTERN           = "HHmmss";
    public static final String TRANSDATEPATTERN           = "MMdd";
    public static final String MONTHANDNOMINUS            = "yyyyMM";
    public static final String WEEKOFYEAR                 = "yyyyww";
    public static final int    MAXSECONDSOFDAY            = 86400;
    public static final int    MILLISOFSECOND             = 1000;

    private static DateUtil    dateUtil                   = null;

    private DateUtil() {

    }

    public static synchronized DateUtil getInstance() {
        if (dateUtil == null) {
            dateUtil = new DateUtil();
        }
        return dateUtil;
    }

    public Date formateStringToDate(String stringDate, String format) {
        if ((stringDate == null) || stringDate.trim().equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(stringDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String formatAll(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String ret = sdf.format(date);
        return ret;
    }

    /**
     * <p>
     * 获取当前时间戳 Timestamp
     * </p>
     * @return
     */
    public Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * <p>
     * 根据输入的Pattern获取当前时间
     * </p>
     * @param pattern
     * @return
     */
    public String getCurrentTimeByPattern(final String pattern) {
        return new DateTime().toString(pattern);
    }

    /**
     * <p>
     * 根据输入的Pattern获取传入时间
     * </p>
     * @param newDate
     * @param pattern
     * @return
     */
    public String getNewTimeByPattern(final Date newDate, final String pattern) {
        return new DateTime(newDate).toString(pattern);
    }

    /**
     * <p>
     * 获取当前时间 格式 yyyy-MM-dd HH:mm:ss
     * </p>
     * @param date
     * @return
     */
    public String getNormTime(Date date) {
        return new DateTime(date).toString(TIMENOWPATTERN);
    }

    /**
     * <p>
     * 获取8位交易日期 格式 yyyyMMdd
     * </p>
     * @return
     */
    public String getTransDate8() {
        return new DateTime().toString(DATENOMINUSPATTERN);
    }

    /**
     * <p>
     * 获取四位交易日期 格式 MMdd
     * </p>
     * @return
     */
    public String getTransDate4() {
        return new DateTime().toString(MONTHANDDATENOMINUSPATTERN);
    }

    /**
     * <p>
     * 获取响应交易日期 格式 MMdd
     * </p>
     * @return
     */
    public String getResponseTransDate() {
        return new DateTime().toString(TRANSDATEPATTERN);
    }

    /**
     * <p>
     * 获取交易时间 格式 HHmmss
     * </p>
     * @return
     */
    public String getTransTime() {
        return new DateTime().toString(TRANSTIMEPATTERN);
    }

    /**
     * <p>
     *  获取不带 "-" 的日期 格式 yyyyMMdd
     * </p>
     * @return
     */
    public String getNowDateNoMinus() {
        return new DateTime().toString(DATENOMINUSPATTERN);
    }

    /**
     * <p>
     *  获取带 "-" 的日期 格式 yyyy-MM-dd
     * </p>
     * @return
     */
    public String getNowDateAndMinus() {
        return new DateTime().toString(DATEANDMINUSPATTERN);
    }

    /**
     * <p>
     *  获取n天前不带 "-" 的日期 格式 yyyyMMdd
     * </p>
     * @param n
     * @return
     */
    public String getPreviousDateNoMinus(int n) {
        return new DateTime().minusDays(n).toString(DATENOMINUSPATTERN);
    }

    /**
     * <p>
     *  获取按天偏移后的日期  格式 yyyy-MM-dd
     * </p>
     * @param n
     * @return
     */
    public String getPreviousDateAndMinus(int n) {
        return new DateTime().minusDays(n).toString(DATEANDMINUSPATTERN);
    }

    /**
     * <p>
     * 获取当前时间 格式 yyyy-MM-dd HH:mm:ss
     * </p>
     * @return
     */
    public String getNowTime() {
        return new DateTime().toString(TIMENOWPATTERN);
    }

    /**
     * <p>
     * 获取当前时间 格式 yyyyMMddHHmmss
     * </p>
     * @return
     */
    public String getNowTimeNoMinus() {
        return new DateTime().toString(TIMENOWPATTERNNOMINUS);
    }

    /**
     * <p>
     * 根据指定时间获取字符串时间 格式 yyyyMMddHHmmss
     * </p>
     * @param date
     * @return
     */
    public String getNowTimeNoMinus(Date date) {
        return new DateTime(date).toString(TIMENOWPATTERNNOMINUS);
    }

    /**
     * <p>
     * 获取当前时分秒 格式 HH:mm:ss
     * </p>
     * @return
     */
    public String getNowTimeNoDate() {
        return new DateTime().toString(TIMENODATEPATTERN);
    }

    /**
     * <p>
     * 获取动态字符型时间
     * </p>
     * @param year
     * @param month
     * @param day
     * @return
     */
    public String getDyncTime(int year, int month, int day, String pattern) {
        return new DateTime().withDate(year, month, day).toString(pattern);
    }

    /**
     * <p>
     * 获取之前或之后天数的时间 格式 yyyy-MM-dd HH:mm:ss
     * </p>
     * @param days
     * @return
     */
    public String getPlusDayToString(int days) {
        return new DateTime().plusDays(days).toString(TIMENOWPATTERN);
    }

    /**
     * <p>
     * 获取之前或之后天数的时间 格式 Date
     * </p>
     * @param days
     * @return
     */
    public Date getPlusDay(int days) {
        return new DateTime().plusDays(days).toDate();
    }

    /**
     * <p>
     * 获取之前或之后分钟数的时间 格式 Date
     * </p>
     * @param minutes
     * @return
     */
    public Date getPlusMinutes(int minutes) {
        return new DateTime().plusMinutes(minutes).toDate();
    }


    /**
     * 获取之前或之后月数的时间 格式 yyyy-MM-dd HH:mm:ss
     * @param months
     * @return
     */
    public String getPlusMonthToString(int months) {
        return new DateTime().plusMonths(months).toString(TIMENOWPATTERN);
    }

    /**
     * <p>
     * 获取按天数偏移后的时间 格式 Date
     * </p>
     * @param months
     * @return
     */
    public Date getPlusMonth(int months) {
        return new DateTime().plusMonths(months).toDate();
    }

    /**
     * <p>
     * 获取指定时间偏移月数后的日期  格式 Date
     * </p>
     * @param beginDate
     * @param months
     * @return
     */
    public Date getPlusMonth(Date beginDate, int months) {
        return new DateTime(beginDate).plusMonths(months).toDate();
    }

    /**
     * <p>
     * 获取之前或之后天数的开始时间戳 格式 yyyy-MM-dd 00:00:00
     * </p>
     * @param days
     * @return
     */
    public String getPlusDaysTimeBegin(int days) {
        return new DateTime().plusDays(days).toString(TIMEBEGINPATTERN);
    }

    /**
     * <p>
     * 获取之前或之后天数的截止时间戳 格式 yyyy-MM-dd 23:59:59
     * </p>
     * @param days
     * @return
     */
    public String getPlusDaysTimeEnd(int days) {
        return new DateTime().plusDays(days).toString(TIMEENDPATTERN);
    }

    /**
     * 获取之前或之后月数的开始时间戳 格式 yyyy-MM-dd 23:59:59
     * @param months
     * @return
     */
    public String getPlusMonthsTimeBegin(int months) {
        return new DateTime().plusMonths(months).toString(TIMEBEGINPATTERN);
    }


    /**
     * 获取之前或之后月数的截止时间戳 格式 yyyy-MM-dd 23:59:59
     * @param months
     * @return
     */
    public String getPlusMonthsTimeEnd(int months) {
        return new DateTime().plusMonths(months).toString(TIMEENDPATTERN);
    }


    /**
     * 获取分钟偏移的时间 格式 yyyy-MM-dd HH:mm:ss
     * @param minutes
     * @return
     */
    public String getPlusMinuteTime(int minutes) {
        return new DateTime().plusMinutes(minutes).toString(TIMENOWPATTERN);
    }

    /**
     * <p>
     * 获取当天中当前秒数
     * </p>
     * @return
     */
    public int getSecondsOfDay() {
        return new DateTime().secondOfDay().get();
    }

    /**
     * 获取当天剩余秒数
     * @return
     */
    public int getRemainSecondsToday() {
        return MAXSECONDSOFDAY - getSecondsOfDay();
    }


    /**
     * 获取当天开始时间戳 格式：yyyy-MM-dd 00:00:00
     * @return
     */
    public String getTodayTimeBegin() {
        return new DateTime().toString(TIMEBEGINPATTERN);
    }

    /**
     * <p>
     * 获取固定月数距今的秒数
     * </p>
     * @param months
     * @return
     */
    public int getPeriodSecondsByMonth(int months) {
        DateTime start = new DateTime();
        DateTime end = new DateTime().plusMonths(months);
        Period p = new Period(start, end, PeriodType.seconds());
        return p.getSeconds();
    }

    /**
     * <p>
     * 获取固定天数之间的秒数
     * </p>
     * @param days
     * @return
     */
    public int getPeriodSecondsByDay(int days) {
        DateTime start = new DateTime();
        DateTime end = new DateTime().plusDays(days);
        Period p = new Period(start, end, PeriodType.seconds());
        return p.getSeconds();
    }

    /**
     * <p>
     * 获取固定月数距今的天数
     * </p>
     * @param months
     * @return
     */
    public int getPeriodDaysByMonth(int months) {
        DateTime start = new DateTime();
        DateTime end = new DateTime().plusMonths(months);
        Period p = new Period(start, end, PeriodType.days());
        return p.getDays();
    }

    /**
     * 获取两个日期之间的秒数
     * @param begin
     * @param end
     * @return
     */
    public int getPeriodSeconds(Date begin, Date end) {
        DateTime beginDt = new DateTime(begin);
        DateTime endDt = new DateTime(end);
        Period p = new Period(beginDt, endDt, PeriodType.seconds());
        return p.getSeconds();
    }

    /**
     * <p>
     * 获取当前字符串型年份 格式：yyyy
     * </p>
     * @return
     */
    public String getYear() {
        return new DateTime().toString(YEARPATTERN);
    }

    /**
     * <p>
     * 获取偏移后的字符串型年份 格式：yyyy
     * </p>
     * @param years
     * @return
     */
    public String getPlusYear(int years) {
        return new DateTime().plusYears(years).toString(YEARPATTERN);
    }

    /**
     * <p>
     * 将MMdd的日期添加年份转换成yyyyMMdd的日期
     * </p>
     * @param transDate
     * @return
     */
    public String getOrignalTransDate(String transDate) {
        if (transDate.length() == 4) {
            String nowDate = getResponseTransDate();
            String year = getYear();
            // 添加年份时候，判断原始交易日期是否在当前日期之前，在之前的话则认为是属于上一年的交易
            if (Integer.parseInt(transDate) > Integer.parseInt(nowDate)) {
                year = getPlusYear(-1);
            }
            transDate = year + transDate;
        }
        return transDate;
    }

    /**
     * <p>
     * 获取字符串时间 格式：yyyyMMddHHmmss
     * </p>
     * @param date
     * @return
     */
    public String getValidDate(Date date) {
        return new DateTime(date).toString(TIMENOWPATTERNNOMINUS);
    }

    /**
     * <p>
     * 根据yyyyMMddHHmmss获取date类型时间
     * </p>
     * @param str
     * @return
     */
    public Date getDateByString(String str) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMENOWPATTERNNOMINUS);
        return sdf.parse(str);
    }

    /**
     * <p>
     * 根据给定格式获取Date类型日期
     * </p>
     * @param str
     * @param pattern
     * @return
     * @throws Exception
     */
    public Date getDateByString(String str, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(str);
    }

    /**
     * <p>
     * 获取字符串日期 格式：yyyyMMdd
     * </p>
     * @param date
     * @return
     */
    public String getStringDateByDate(Date date) {
        return new DateTime(date).toString(DATENOMINUSPATTERN);
    }

    /**
     * <p>
     * 获取交易时间 格式：HHmmss
     * </p>
     * @param date
     * @return
     */
    public String getStringTimeByDate(Date date) {
        return new DateTime(date).toString(TRANSTIMEPATTERN);
    }

    /**
     * <p>
     * 获取按秒数偏移日期 Date
     * </p>
     * @param seconds
     * @return
     */
    public Date getPlusSeconds(int seconds) {
        return new DateTime().plusSeconds(seconds).toDate();
    }

    /**
     * <p>
     * 获取1970年到现在的所有天数
     * </p>
     * @return
     */
    public Long getCurrDays() {
        long currTime = System.currentTimeMillis();
        long perDayTime = MAXSECONDSOFDAY * MILLISOFSECOND;
        return currTime / perDayTime;
    }

    /**
     * <p>
     * 获取当前月份 yyyyMM
     * </p>
     * @return
     */
    public String getYearMonth() {
        return new DateTime().toString(MONTHANDNOMINUS);
    }

    /**
     * 获取两个时间点之间的自然天数
     * @param begin
     * @param end
     * @return
     */
    public Integer getPeriodDays(Date begin, Date end) {
        DateTime beginDt = new DateTime(begin);
        DateTime endDt = new DateTime(end);
        Period p = new Period(beginDt, endDt, PeriodType.days());
        return p.getDays() + 1;
    }

    /**
     * <p>
     * 计算两个时间点之间的自然周数
     * </p>
     * @param start
     * @param end
     * @return
     */
    public Integer getPeriodWeeks(Date start, Date end) {
        DateTime startDate = new DateTime(start);
        DateTime endDate = new DateTime(end);

        int plusDays = Days.daysBetween(startDate, endDate).getDays();
        int week = startDate.getDayOfWeek();

        int weekPlus = (7 - week) + 1;

        int weeks = 1;

        if (plusDays >= weekPlus) {
            DateTime plusStartDate = startDate.plusDays(weekPlus);
            int days = Days.daysBetween(plusStartDate, endDate).getDays();
            if (days < 7) {
                weeks++;
            } else {
                // 计算完整周数
                int completeWeeks = Weeks.weeksBetween(plusStartDate, endDate).getWeeks();

                // joda time 在计算完整周数时最后一周未计算在内
                weeks++;
                System.out.println(weeks + completeWeeks);
                weeks = weeks + completeWeeks;
            }
        }
        return weeks;

    }

    /**
     * <p>
     * 计算两个时间点之间的自然月数
     * </p>
     * @param start
     * @param end
     * @return
     */
    public Integer getPeriodMonths(Date start, Date end) {

        DateTime startDate = new DateTime(start);
        DateTime endDate = new DateTime(end);

        int months = 1;
        int plusDays = Days.daysBetween(startDate, endDate).getDays();

        int lasday = startDate.dayOfMonth().getMaximumValue();
        int day = startDate.getDayOfMonth();

        int monthPlusDays = (lasday - day) + 1;

        if (plusDays > monthPlusDays) {
            DateTime nextMonthStart = startDate.plusDays(monthPlusDays);
            // 获取整数月
            int completeMonths = Months.monthsBetween(nextMonthStart, endDate).getMonths();
            months++;
            months = months + completeMonths;
        }
        return months;
    }

    /**
     * <p>
     * 获取当前是一周的第几天
     * 0 周日
     * 1 周一
     * 2 周二
     * 3 周三
     * 4 周四
     * 5 周五
     * 6 周六
     * </p>
     * @return
     */
    public Integer getDayOfWeek() {
        Integer days = new DateTime().getDayOfWeek();
        if (days == 7) {
            days = 0;
        }
        return days;
    }

    /**
     * <p>
     * 获取当前是一周的第几天
     * 0 周日
     * 1 周一
     * 2 周二
     * 3 周三
     * 4 周四
     * 5 周五
     * 6 周六
     * </p>
     * @param date
     * @return
     */
    public Integer getDayOfWeek(Date date) {
        Integer days = new DateTime(date).getDayOfWeek();
        if (days == 7) {
            days = 0;
        }
        return days;
    }

    /**
     * <p>
     * 获取JAVA中的年周,周日为一周第一天
     * </p>
     * @return
     */
    public String getYearWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat(WEEKOFYEAR);
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * <p>
     * 获取中国年周,周日为一周最后一天
     * </p>
     * @return
     */
    public String getChinaYearWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat(WEEKOFYEAR);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return sdf.format(cal.getTime());
    }

    public static void main(String[] args) throws Exception {

        Date start = new DateTime(2015, 12, 27, 00, 00, 00).toDate();
        Date end = new DateTime(2016, 1, 10, 23, 59, 59).toDate();
        System.out.println(getInstance().getPeriodDays(start, end));

        System.out.println(getInstance().getDayOfWeek(end));

        // DateTime start = new DateTime(2015, 12, 14, 00, 00, 00);
        // DateTime end = new DateTime(2016, 2, 11, 23, 59, 59);
        // Period dp = new Period(start, end, PeriodType.days());
        // System.out.println(dp.getDays());
        //
        // Period wp = new Period(start, end, PeriodType.weeks());
        // System.out.println(wp.getWeeks());
        //
        // Period mp = new Period(start, end, PeriodType.months());
        // System.out.println(mp.getMonths());
    }
}
