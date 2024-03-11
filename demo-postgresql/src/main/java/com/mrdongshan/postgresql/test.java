package com.mrdongshan.postgresql;

import com.mrdongshan.postgresql.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {
    public static void main(String[] args) {
        String startTime = "2024-01-31 15:07:48";
        String endTime = "2024-02-05 15:32:56";

        List<String> list = new ArrayList<>();
        list.add("2024-01-31 23:51:17 - 2024-02-01 09:15:15");
        list.add("2024-02-01 23:39:34 - 2024-02-02 09:55:17");
        list.add("2024-02-03 00:51:53 - 2024-02-03 12:56:25");
        list.add("2024-02-03 23:27:08 - 2024-02-04 11:01:21");
        list.add("2024-02-05 00:21:17 - 2024-02-05 10:20:52");
//        list.add("2024-01-31 23:19:01 - 2024-02-01 09:17:04");


        long totalTime = getIntervalTime(startTime, endTime);
        long excludeTime = getExcludeTime(list);
        long timeStampDifference = totalTime - excludeTime;
        long hours = timeStampDifference / (60 * 60 * 1000);
        long minutes = (timeStampDifference % (60 * 60 * 1000)) / (60 * 1000);

        System.out.println("两个日期之间相差 " + hours + " 小时 " + minutes + " 分钟");

    }

    /**
     * 获取两个时间之间的间隔时间
     */
    private static long getIntervalTime(String startTime, String endTime) {
        Date startDate = DateUtil.stringToDate(startTime, "yyyy-MM-dd HH:mm:ss");
        Date endDate = DateUtil.stringToDate(endTime, "yyyy-MM-dd HH:mm:ss");
        return Math.abs(endDate.getTime() - startDate.getTime());
    }

    private static long getExcludeTime(List<String> list) {
        long totalTime = 0;
        for (String s : list) {
            String startTime = s.split(" - ")[0];
            String endTime = s.split(" - ")[1];
            totalTime += getIntervalTime(startTime, endTime);
        }
        return totalTime;
    }

}
