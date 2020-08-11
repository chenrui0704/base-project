package com.base.frame.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 */
public class DateUtil {

    public static long DAY_TIMESTAMP = 1000 * 60 * 60 * 24;
    public static long WEEK_TIMESTAMP = DAY_TIMESTAMP * 7;
    public static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String YMD_PATTERN = "yyyy-MM-dd";

    /**
     * <p>功能: 根据两个日期的时间差获取对应的相差的天数</p>
     *
     * <p>场景1: 2020-02-25 15:07:00  2020-02-26 12:20:50  return 0 </p>
     * <p>场景2: 2020-02-25 15:07:00  2020-02-26 16:20:50  return 1 </p>
     * <p>场景3: 2020-02-25 15:07:00  2020-02-23 12:20:50  return 2 </p>
     */
    public static long getDaysDiff(Date d1, Date d2) {
        return getTimeStampByDateDiff(d1, d2) / DAY_TIMESTAMP;
    }

    /**
     * <p>功能: 根据两个日期的时间差获取对应的相差的周差</p>
     *
     * <p>场景1: 2020-02-26 10:53:00  2020-02-19 12:20:50  return 0 </p>
     * <p>场景2: 2020-02-26 15:53:00  2020-02-19 10:20:50  return 1 </p>
     * <p>场景3: 2020-02-26 15:53:00  2020-03-05 10:20:50  return 1 </p>
     */
    public static long getWeekDiff(Date d1, Date d2) {
        return getTimeStampByDateDiff(d1, d2) / WEEK_TIMESTAMP;
    }

    /**
     * 获取两个时间相差的时间戳
     */
    public static long getTimeStampByDateDiff(Date d1, Date d2) {
        return compare(d1, d2) ? (d1.getTime() - d2.getTime()) : (d2.getTime() - d1.getTime());
    }

    /**
     * 比较两个时间的大小
     */
    public static boolean compare(Date d1, Date d2) {
        return d1.getTime() > d2.getTime();
    }

    /**
     * 获取当前时间，格式为: yyyy-MM-dd HH:mm:ss
     * <p>例如: 2020-02-26 14:17:30</p>
     */
    public static String getCurrentTime() {
        return convertString(new Date());
    }

    /**
     * 获取当前时间，格式为: yyyy-MM-dd
     * <p>例如: 2020-02-26</p>
     */
    public static String getCurrentYMDTime() {
        return convertStringByPattern(new Date(), YMD_PATTERN);
    }

    /**
     * 根据传输的时间转换, 格式为: yyyy-MM-dd HH:mm:ss
     */
    public static String convertString(Date date) {
        return convertStringByPattern(date, DEFAULT_PATTERN);
    }

    /**
     * 根据传输的时间，传输的格式进行转换
     */
    public static String convertStringByPattern(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    // 获取之前的时间
    public static Date getBeforeDate(Integer beforeDays){
        return new Date(System.currentTimeMillis() - DAY_TIMESTAMP * beforeDays);
    }

}
