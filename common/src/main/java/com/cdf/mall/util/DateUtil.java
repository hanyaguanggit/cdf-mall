package com.cdf.mall.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * @author daimingdong
 * 日期工具类
 */
@Slf4j
public class DateUtil {

    public static String PATTERN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String PATTERN_YYYYMMDD = "yyyyMMdd";

    public static String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    public static String PATTERN_HHMMDD = "HHmmss";

    public static String PATTERN_HH_MM_DD = "HH:mm:ss";

    public static ZoneId ZONE = ZoneId.of("Asia/Shanghai");


    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        LocalDateTime localDateTime = instant.atZone(ZONE).toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime;
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZONE);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDate
     */
    public static Date localDateTime2Date(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZONE);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    //*******日期格式转换****************************************************************************
    /**
     * default
     *
     * @param date
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String DateTimeToStr(Date date) {
        return DateToStr(date, PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @param date
     * @param format 日期格式
     * @return String
     */
    public static String DateToStr(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            log.error("Date 转 String 类型失败: " + e);
            return null;
        }
    }


    /**
     * default
     *
     * @param sDateTime yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date sDateTimeToDate(String sDateTime) {
        return strToDate(sDateTime, PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @param sDate
     * @param format 日期格式
     * @return String
     */
    public static Date strToDate(String sDate, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(sDate);
        } catch (Exception e) {
            log.error("String 转 Date 类型失败: " + e);
            return null;
        }
    }

    /**
     * 将毫秒的UTC时间转换格式为Date
     *
     * @param utc
     * @return
     */
    public static Date getUTCToDate(String utc) {
        return new Date(Long.parseLong(utc));
    }

    /**
     * date转utc时间
     *
     * @param date
     * @return 秒
     */
    public static String getDate2UTCSeconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String time = new Long(calendar.getTimeInMillis() / 1000).toString();
        return time;
    }


    /**
     * 时间戳比较  时间参数要统一精度
     * @param src
     * @param dst
     * @return
     */
    public static int compareStamp(String src,String dst){
        if(Long.parseLong(src) > Long.parseLong(dst)){
            return 1;
        }else if(Long.parseLong(src) == Long.parseLong(dst)){
            return 0;
        }else{
            return -1;
        }
    }

    /**
     * date转utc时间
     *
     * @param date
     * @return 毫秒
     */
    public static String getDate2UTCMills(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String time = new Long(calendar.getTimeInMillis()).toString();
        return time;
    }

    // ******************日期计算*********************************************************

    /**
     * 获取某天的开始和结束时间
     * @return date
     */
    public static Date[] getStartEndDate(Date date) {

        Calendar sc = Calendar.getInstance();
        Calendar ec = Calendar.getInstance();
        sc.setTime(date);
        sc.set(Calendar.HOUR_OF_DAY, 0);
        sc.set(Calendar.MINUTE, 0);
        sc.set(Calendar.SECOND, 0);
        sc.set(Calendar.MILLISECOND, 0);

        ec.setTime(date);
        ec.set(Calendar.HOUR_OF_DAY, 23);
        ec.set(Calendar.MINUTE, 59);
        ec.set(Calendar.SECOND, 59);
        ec.set(Calendar.MILLISECOND, 999);

        Date[] str = new Date[2];
        str[0] = sc.getTime();
        str[1] = ec.getTime();
        log.info(str[0] + "-----" + str[1]);
        return str;
    }


    /**
     * 获取某天的开始和结束时间
     * @return String
     */
    public static String[] getStartEndStr(Date date) {

        Calendar sc = Calendar.getInstance();
        Calendar ec = Calendar.getInstance();
        sc.setTime(date);
        sc.set(Calendar.HOUR_OF_DAY, 0);
        sc.set(Calendar.MINUTE, 0);
        sc.set(Calendar.SECOND, 0);
        sc.set(Calendar.MILLISECOND, 0);

        ec.setTime(date);
        ec.set(Calendar.HOUR_OF_DAY, 23);
        ec.set(Calendar.MINUTE, 59);
        ec.set(Calendar.SECOND, 59);
        ec.set(Calendar.MILLISECOND, 999);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start_create = sdf.format(sc.getTime());
        String end_create = sdf.format(ec.getTime());

        String[] str = new String[2];
        str[0] = start_create;
        str[1] = end_create;
        log.info(str[0] + "-----" + str[1]);
        return str;
    }

    /**
     * 比较两个时间的大小
     *
     * @return int
     */
    public static int compareDate(Date dt1, Date dt2) {
        if (dt1.getTime() > dt2.getTime()) {
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {
            return 2;
        } else {//相等
            return 0;
        }
    }

    /**
     * 相隔有多少天
     *
     * @param sd
     * @param ed
     * @return
     */
    public static int getDays(Date sd, Date ed) {
        return (int) ((ed.getTime() - sd.getTime()) / (24 * 3600 * 1000));
    }

    /**
     * 开始时间 start 在 结束时间 end 之前返回 true，否则 false
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isDateBefore(Date start, Date end) {
        GregorianCalendar sgc = new GregorianCalendar();
        GregorianCalendar egc = new GregorianCalendar();
        sgc.setTime(start);
        egc.setTime(end);

        return sgc.before(egc);
    }

    /**
     * 判断 date 在 开始时间 start 与 结束时间 end 之间返回 true, 否则 false
     *
     * @param start -
     *              开始时间
     * @param end   -
     *              结束时间
     * @param date  -
     *              要判断的时间
     * @return
     */
    public static boolean isDateBetweenStartAndEnd(Date start, Date end, Date date) {
        if ((isDateBefore(start, end)) && (isDateBefore(start, date)) && (isDateBefore(date, end))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param date
     * @return 若指定时间在当前时间之后则返回true, 否则返回false
     */
    public static boolean afterCurDate(Date date) {
        GregorianCalendar now = new GregorianCalendar();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return c.after(now);
    }

    /**
     * Checks if two date objects are on the same day ignoring time
     *
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either date is <code>null</code>
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * Checks if two calendar objects are on the same day ignoring time
     *
     * @param cal1 the first calendar, not altered, not null
     * @param cal2 the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
                .get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }


    /**
     * 日期相加
     *
     * @param date 日期
     * @param val  天数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DATE, val);
        return gc.getTime();
    }

    /**
     * 日期相减
     *
     * @param date 日期
     * @param val  天数
     * @return 返回相减后的日期
     */
    public static Date subDate(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DATE, -val);
        return gc.getTime();
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param val  年
     * @return 返回相加后的日期
     */
    public static Date addYear(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.YEAR, val);
        return gc.getTime();
    }

    /**
     * 日期相减
     *
     * @param date 日期
     * @param val  年
     * @return 返回相减后的日期
     */
    public static Date subYear(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.YEAR, -val);
        return gc.getTime();
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param val  月
     * @return 返回相加后的日期
     */
    public static Date addMonth(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MONTH, val);
        return gc.getTime();
    }

    /**
     * 日期相减
     *
     * @param date 日期
     * @param val  月
     * @return 返回相减后的日期
     */
    public static Date subMonth(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MONTH, -val);
        return gc.getTime();
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param val  小时
     * @return 返回相加后的日期
     */
    public static Date addHour(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.HOUR, val);
        return gc.getTime();
    }

    /**
     * 日期相减
     *
     * @param date 日期
     * @param val  小时
     * @return 返回相减后的日期
     */
    public static Date subHour(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.HOUR, -val);
        return gc.getTime();
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param val  分钟
     * @return 返回相加后的日期
     */
    public static Date addMinute(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MINUTE, val);
        return gc.getTime();
    }

    /**
     * 日期相减
     *
     * @param date 日期
     * @param val  分钟
     * @return 返回相减后的日期
     */
    public static Date subMinute(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MINUTE, -val);
        return gc.getTime();
    }


    /**
     * 日期相加
     *
     * @param date 日期
     * @param val  秒
     * @return 返回相加后的日期
     */
    public static Date addSencond(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.SECOND, val);
        return gc.getTime();
    }

    /**
     * 日期相减
     *
     * @param date 日期
     * @param val  秒
     * @return 返回相减后的日期
     */
    public static Date subSencond(Date date, int val) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.SECOND, -val);
        return gc.getTime();
    }


    // ***************************************************************************

    /**
     * 根据 date 判断这一天是这一年的第几天
     *
     * @param date
     * @return
     */
    public static int getDayOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算某一月份的最大天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int sumDayByYearMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1); // 注意,Calendar对象默认一月为0
        return c.getActualMaximum(Calendar.DAY_OF_MONTH); // month 月份的天数
    }

    /**
     * 得到某月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 得到某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 得到某月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 得到某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }


    /**
     * 获取某天开始的那一刻
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Calendar getDateBegin(int year, int month, int date) {
        Calendar begin_time = Calendar.getInstance();
        begin_time.set(Calendar.YEAR, year);
        begin_time.set(Calendar.MONTH, month - 1);
        begin_time.set(Calendar.DATE, date);
        begin_time.set(Calendar.HOUR_OF_DAY, 0);
        begin_time.set(Calendar.MINUTE, 0);
        begin_time.set(Calendar.SECOND, 0);
        begin_time.set(Calendar.MILLISECOND, 0);
        return begin_time;
    }

    /**
     * 清除日历的时间字段
     *
     * @param cal
     */
    public static void resetTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    // ***************************************************************************

    /**
     * 根据 开始日期 与 结束日期 得到它们之间的所有日期 包括 开始日期 与 结束日期
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Date> getDateList(Date startDate, Date endDate) {
        Date tempDate = startDate;
        List<Date> dateList = new ArrayList();
        if (isSameDay(startDate, endDate)) {
            dateList.add(tempDate);
        } else {
            while (tempDate.before(endDate)) {
                dateList.add(tempDate);
                tempDate = DateUtil.addDate(tempDate, 1);
            }
            dateList.add(tempDate);
        }
        return dateList;
    }

    /**
     * 根据 开始日期 与 结束日期 得到它们之间的所有日期 包括 开始日期 与 结束日期
     *
     * @param startDate -
     *                  开始日期 yyyy-MM-dd
     * @param endDate   -
     *                  结束日期 yyyy-MM-dd
     * @return List<yyyy-MM-dd>
     */
    public static List<String> getDateToStrList(String startDate, String endDate) {
        return getDateList(startDate, endDate, "yyyy-MM-dd");
    }

    /**
     * 根据 开始日期 与 结束日期 得到它们之间的所有日期 包括 开始日期 与 结束日期
     *
     * @param startDate -
     *                  开始日期
     * @param endDate   -
     *                  结束日期
     * @param format    -
     *                  定义 startDate, endDate 及 返回数据 的格式
     * @return
     */
    public static List<String> getDateList(String startDate, String endDate, String format) {
        List<String> sDateList = new ArrayList<String>();
        Date periodDate = strToDate(startDate, format);
        if (startDate.equals(endDate)) {
            sDateList.add(DateToStr(periodDate, format));
        } else {
            while (periodDate.before(strToDate(endDate, format))) {
                sDateList.add(DateToStr(periodDate, format));
                periodDate = DateUtil.addDate(periodDate, 1);
            }
            sDateList.add(DateToStr(periodDate, format));
        }
        return sDateList;
    }

    /**
     * 根据年月得到 month 月所有的日期
     *
     * @param year
     * @param month
     * @return
     */
    public static List<Date> getYearMonthAllDate(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        List<Date> dateList = new ArrayList<Date>();
        int size = c.getActualMaximum(Calendar.DATE);
        for (int i = 0; i < size; i++) {
            c.set(Calendar.DATE, i + 1);
            dateList.add(c.getTime());
        }
        return dateList;
    }

    /**
     * 判断某年是否为闰年
     *
     * @param year
     * @return
     */
    public boolean isLeapYear(int year) {
        GregorianCalendar gc = new GregorianCalendar();
        return gc.isLeapYear(year);
    }


    /**
     * 合并日期和时间
     *
     * @param date
     * @param time
     * @return
     */
    public static Calendar mergeDateTime(Date date, Time time) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        if (time != null) {
            Calendar temp = Calendar.getInstance();
            temp.setTime(time);
            cal.set(Calendar.HOUR_OF_DAY, temp.get(Calendar.HOUR_OF_DAY));
            cal.set(Calendar.MINUTE, temp.get(Calendar.MINUTE));
            cal.set(Calendar.SECOND, temp.get(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, temp.get(Calendar.MILLISECOND));
        }
        return cal;
    }


    // ----------------------------------------------------------------------- Other

    public static String getMonthStr(String str) {
        String monthOfYear[] = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        if ("01".equals(str)) {
            str = monthOfYear[0];
        } else if ("02".equals(str)) {
            str = monthOfYear[1];
        } else if ("03".equals(str)) {
            str = monthOfYear[2];
        } else if ("04".equals(str)) {
            str = monthOfYear[3];
        } else if ("05".equals(str)) {
            str = monthOfYear[4];
        } else if ("06".equals(str)) {
            str = monthOfYear[5];
        } else if ("07".equals(str)) {
            str = monthOfYear[6];
        } else if ("08".equals(str)) {
            str = monthOfYear[7];
        } else if ("09".equals(str)) {
            str = monthOfYear[8];
        } else if ("10".equals(str)) {
            str = monthOfYear[9];
        } else if ("11".equals(str)) {
            str = monthOfYear[10];
        } else if ("12".equals(str)) {
            str = monthOfYear[11];
        } else {
            str = "ERROR";
        }
        return str;
    }

    /**
     * 根据 英文月份全称 得到 数字 如: January => 1 </br>若返回为 0 即表明 strMonth 非月份字符串
     *
     * @param strMonth
     * @return
     */
    public static int getNumByStrMonth(String strMonth) {
        if (strMonth.equals("January")) {
            return 1;
        } else if (strMonth.equals("February")) {
            return 2;
        } else if (strMonth.equals("March")) {
            return 3;
        } else if (strMonth.equals("April")) {
            return 4;
        } else if (strMonth.equals("May")) {
            return 5;
        } else if (strMonth.equals("June")) {
            return 6;
        } else if (strMonth.equals("July")) {
            return 7;
        } else if (strMonth.equals("August")) {
            return 8;
        } else if (strMonth.equals("September")) {
            return 9;
        } else if (strMonth.equals("October")) {
            return 10;
        } else if (strMonth.equals("November")) {
            return 11;
        } else if (strMonth.equals("December")) {
            return 12;
        } else {
            return 0; // strMonth 非 月份
        }
    }

}
