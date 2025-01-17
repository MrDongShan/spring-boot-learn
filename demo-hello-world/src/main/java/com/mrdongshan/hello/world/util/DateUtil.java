package com.mrdongshan.hello.world.util;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 日期工具
 */
public class DateUtil {

    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

    public static final TimeZone CHINA_ZONE = TimeZone.getTimeZone("Asia/Shanghai");

    public static final TimeZone UTC_ZONE = TimeZone.getTimeZone("UTC");

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

    public static Date getDateMin(Date date) {
        return Date.from(dateToLocalDate(date).atTime(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());
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
     * 将时间戳转换为 yyyy-MM-dd HH:mm:ss
     *
     * @param timestamp 时间戳
     */
    public static String convertTimestampToDate(long timestamp) {
        return convertTimestampToDate(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    public static String convertTimestampToDate(long timestamp, String pattern) {
        // 将13位时间戳转换为10位时间戳（秒级时间戳）
        if (String.valueOf(timestamp).length() == 13) {
            timestamp = timestamp / 1000;
        }

        // 使用Instant类将秒级时间戳转换为LocalDateTime对象
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());

        // 使用DateTimeFormatter格式化LocalDateTime对象为"yyyy-MM-dd HH:mm:ss"格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * @return 昨天的日期
     */
    public static String getYesterdayIsoFormat() {
        return getYesterdayIsoFormat(LocalDate.now());
    }

    public static String getYesterdayIsoFormat(LocalDate localDate) {
        return LocalDate.ofYearDay(localDate.getYear(), localDate.getDayOfYear() - 1).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String getYesterdayIsoFormat(Date date) {
        return getYesterdayIsoFormat(dateToLocalDate(date));
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
     * 相差小于24小时的等于0天
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
        if (strDate != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime parse = LocalDate.parse(strDate, dateTimeFormatter).atStartOfDay();
            return Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }

    public static Date stringToDate(String strDate, String pattern) {
        if (strDate != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime parse = LocalDateTime.parse(strDate, dateTimeFormatter);
            return Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }


    public static String toRangeDateChina(long time, String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        format.setTimeZone(CHINA_ZONE);
        return format.format(time);
    }

    /**
     * 带TZ的时间转换成Timestamp
     */
    public static long TZtoTimestamp(String time) {
        //字符串时间，带T带Z带毫秒值
        String stime = "2020-11-18T04:31:40.886Z";
        //创建对应的pattern，注意T和Z两个字符使用单引号引起来，毫秒值使用大写S表示
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //字符串时间转换成时间类型
        LocalDateTime date = LocalDateTime.parse(stime, pattern);
        //时间类型转时间戳类型
        return date.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * String转 LocalDateTime
     *
     * @param dateTimeStr 日期的字符串
     * @param pattern     格式，类似 yyyy-MM-dd HH:mm:ss
     * @return LocalDateTime
     */
    public static LocalDateTime strToLocalDateTime(String dateTimeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    /**
     * date转换成LocalDateTime
     *
     * @param date 要转换的日期
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        return instant.atZone(zoneId).toLocalDateTime();
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
     * 获取今日零点零分零秒时间戳
     */
    public static Long getWeeHoursDate() {
        long nowTime = SystemClock.millisClock().now();
        return nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
    }

    /**
     * 获取零点零分零秒时间戳
     */
    public static Long getWeeHoursDate(long time) {
        return time - ((time + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
    }

    /**
     * 判断是否处于夜间时间(晚八点到早十点)
     * 在夜间时间：true
     */
    public static Boolean isAtNight(long time) {
        return isAtNight(new Date(time));
    }

    public static Boolean isAtNight(Date date) {
        // 创建一个 Calendar 实例并设置时间为当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 获取小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24小时制
        if (hour >= 10 && hour < 20) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 获取两个日期之间所有的日期, 包含开始日期和结束日期
     */
    public static List<LocalDate> getLocalDatesBetween(Long startDate, Long endDate) {
        return getLocalDatesBetween(longToLocalDate(startDate), longToLocalDate(endDate));
    }

    /**
     * 获取两个日期之间所有的日期, 包含开始日期和结束日期
     */
    public static List<LocalDate> getLocalDatesBetween(String startDate, String endDate) {
        return getLocalDatesBetween(strToLocalDate(startDate), strToLocalDate(endDate));
    }

    /**
     * 获取两个日期之间所有的日期, 包含开始日期和结束日期
     */
    public static List<LocalDate> getLocalDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return dates;
    }

    public static LocalDate longToLocalDate(Long date) {
        return Instant.ofEpochMilli(date)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * 获取两个日期之间所有的日期, 包含开始日期和结束日期
     */
    public static List<String> getStrDatesBetween(Long startDateLong, Long endDateLong) {
        LocalDate startDate = Instant.ofEpochMilli(startDateLong)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(endDateLong)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        List<LocalDate> dates = getLocalDatesBetween(startDate, endDate);

        return dates.stream()
                .map(DateUtil::localDateToStr).collect(Collectors.toList());
    }

    public static List<String> getStrDatesBetween(String startDateStr, String endDateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return dates.stream()
                .map(item -> item.format(formatter)).collect(Collectors.toList());
    }


    public static String localDateToStr(LocalDate date) {
        return localDateToStr(date, "yyyy-MM-dd");
    }

    public static String localDateToStr(LocalDate date, String pattern) {
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // 将LocalDate格式化为字符串
        return date.format(formatter);
    }

    public static LocalDate strToLocalDate(String date) {
        return strToLocalDate(date, "yyyy-MM-dd");
    }

    public static LocalDate strToLocalDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // 解析字符串为LocalDate对象
        return LocalDate.parse(date, formatter);
    }

    /**
     * 获取今日凌晨一点的时间戳
     */
    public static Long getOneHoursDate() {
        long nowTime = SystemClock.millisClock().now();
        return nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L)) + 3600000L;
    }

    /**
     * 获取明天凌晨一点的时间戳
     */
    public static Long getOneHoursDateTomorrow() {
        long nowTime = SystemClock.millisClock().now();
        return nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L)) + 3600000L + 86400000L;
    }

    public static String minuteProcessing(Integer minute) {
        //如果传入的分钟是0，默认24小时
        if (0 == minute) {
            return 24 + "小时";
        }
        //如果分钟小于60，默认返回分钟
        if (0 < minute && minute < 60) {
            return minute + "分钟";
        }
        //如果分钟小于24小时（1440分钟），返回小时和分钟
        if (60 <= minute && minute < 1440) {

            if (minute % 60 == 0) {
                int h = minute / 60;
                return h + "小时";
            } else {
                int h = minute / 60;
                int m = minute % 60;
                return h + "小时" + m + "分钟";
            }

        }
        //如果分钟大于1天
        if (minute >= 1440) {

            int d = minute / 60 / 24;
            int h = minute / 60 % 24;
            int m = minute % 60;
            String s1 = null;
            if (d > 0) {
                s1 = d + "天";
            }
            //h如果计算大于等于1再展示，否则只展示天和分钟
            if (h >= 1) {
                s1 += h + "小时";
            }
            if (m > 0) {
                s1 += m + "分钟";
            }

            return s1;
        }
        return null;
    }

    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();
    }

    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        long seconds = timestamp / 1000;
        Instant instant = Instant.ofEpochSecond(seconds);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static String localDateTimeToStr(LocalDateTime localDateTime) {
        return localDateTimeToStr(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String localDateTimeToStr(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 判断两个时间相差几分钟
     */
    public static long getMinutesBetweenTimestamps(long timestamp1, long timestamp2) {
        long diffInMillis = Math.abs(timestamp1 - timestamp2);
        return TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
    }
}
