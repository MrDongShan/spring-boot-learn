package com.mrdongshan.postgresql.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 */
public class DateUtil {
    /**
     * @return 当天最后一秒
     */
    public static Date getTodayMax() {
        return Date.from(LocalDate.now().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @return 当天第一秒
     */
    public static Date getTodayMin() {
        return Date.from(LocalDate.now().atTime(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @return 当天周几
     */
    public static int getDayOfWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }


    /**
     * @return 当前几点 -24小时制
     */
    public static int getDayOfHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * @return yyyy-mm-dd格式的日期
     */
    public static String getTodayIsoFormat() {
        return LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * @return HH:mm:ss格式的时间
     */
    public static String getTimeIsoFormat() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * @return 昨天的日期
     */
    public static String getYesterdayIsoFormat() {
        return LocalDate.ofYearDay(LocalDate.now().getYear(), LocalDate.now().getDayOfYear() - 1).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * 获取当天的中午时间,中午的时间是中午12点
     */
    public static Date getCurrentNoonTime() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.NOON);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 当前时间是上午
     *
     * @return true:上午  false:下午
     */
    public static Boolean isAM() {
        return System.currentTimeMillis() <= getCurrentNoonTime().getTime();
    }

    /**
     * 获取两个日期相差几天
     */
    public static Integer durationBetweenTwoDate(Date startTime, Date endTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime startDate = LocalDateTime.ofInstant(startTime.toInstant(), zoneId);
        LocalDateTime endData = LocalDateTime.ofInstant(endTime.toInstant(), zoneId);
        return (int) Duration.between(startDate, endData).toDays();
    }

    /**
     * 获取某个日期与今天相差几天
     */
    public static Integer durationBetweenTwoDate(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime currData = LocalDateTime.ofInstant(new Date().toInstant(), zoneId);
        LocalDateTime reserveDate = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        return (int) Duration.between(reserveDate, currData).toDays();
    }

    /**
     * 当前时间是否超过了最晚的刷号时间: 昨天的号最晚可以今天X点刷
     * 比最晚时间早: true 比最晚时间晚: false
     */
    public static Boolean noTimeout() {
        Calendar calendar = Calendar.getInstance();
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        // 分
        calendar.set(Calendar.MINUTE, 30);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        Date date = new Date();
        return date.before(calendar.getTime());
    }


    public static String dateToString(Date date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);

        return dateTimeFormatter.format(localDateTime);
    }

    public static String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date stringToDate(String strDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime parse = LocalDate.parse(strDate, dateTimeFormatter).atStartOfDay();
        return Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date stringToDate(String strDate, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime parse = LocalDateTime.parse(strDate, dateTimeFormatter);
        return Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate stringToLocalDate(String strDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(strDate, dateTimeFormatter);
    }

    public static LocalDateTime stringToLocalDateTime(String strDate, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(strDate, dateTimeFormatter);
    }

    public static LocalDateTime stringToLocalDateTime(String strDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.parse(strDate, dateTimeFormatter);
    }

    /**
     * str 转13位时间戳
     */
    public static Long stringToTime(String strDate, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime parse = LocalDateTime.parse(strDate, dateTimeFormatter);
        return parse.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    /**
     * Date 转为 LocalDate
     *
     * @param date 要转换的时间
     * @return LocalDate;
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate 转为 String
     *
     * @param date 要转换的时间
     * @return Date;
     */
    public static String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 获取某个日期前一天的日期
     *
     * @param strDate 某个日期
     * @return String
     */
    public static String getYesterday(String strDate) {
        LocalDate localDate = stringToLocalDate(strDate).minusDays(1);
        return localDateToString(localDate);
    }

    /**
     * LocalDate 转为 String
     *
     * @param date 要转换的时间
     * @return Date;
     */
    public static String localDateToString(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * LocalDate 转为 Date
     *
     * @param date 要转换的时间
     * @return Date;
     */
    public static Date localDateToDate(LocalDate date) {
        return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * LocalDateTime 转为 Date
     *
     * @param date 要转换的时间
     * @return Date;
     */
    public static Date localDateTimeToDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取今日零点零分零秒时间戳
     */
    /*public static Long getWeeHoursDate() {
        long nowTime = SystemClock.millisClock().now();
        return nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
    }*/

    /**
     * 获取今日凌晨一点的时间戳
     */
    /*public static Long getOneHoursDate() {
        long nowTime = SystemClock.millisClock().now();
        return nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L)) + 3600000L;
    }*/
}
