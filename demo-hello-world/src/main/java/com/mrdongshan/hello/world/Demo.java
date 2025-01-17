package com.mrdongshan.hello.world;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.RandomUtil;
import com.mrdongshan.hello.world.util.DateUtil;
import com.mrdongshan.hello.world.util.SystemClock;
import com.mrdongshan.hello.world.util.WordUtils;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        test5();
    }

    private static void test1() {
        // 如果是同一天的日期，结果是0
        Date date = new Date();
        Date date1 = new Date(System.currentTimeMillis() + 1000);
        Integer i = DateUtil.durationBetweenTwoDate(date1, date);
        System.err.println(i);
        // 如果是相差时间不超过24小时的两个日期，结果是0
        Date date2 = DateUtil.stringToDate("2024-01-01 12:00:00", "yyyy-MM-dd HH:mm:ss");
        Date date3 = DateUtil.stringToDate("2024-01-02 10:00:00", "yyyy-MM-dd HH:mm:ss");

        Integer j = DateUtil.durationBetweenTwoDate(date2, date3);
        System.err.println(j);

        // 如果是相差时间等于24小时的两个日期，结果是1
        Date date4 = DateUtil.stringToDate("2024-01-01 12:00:00", "yyyy-MM-dd HH:mm:ss");
        Date date5 = DateUtil.stringToDate("2024-01-02 12:00:00", "yyyy-MM-dd HH:mm:ss");

        Integer z = DateUtil.durationBetweenTwoDate(date4, date5);
        System.err.println(z);

        String yesterdayIsoFormat = DateUtil.getYesterdayIsoFormat(date);
        System.err.println(yesterdayIsoFormat);
    }

    private static void test2() {
        // 如果是相差时间不超过24小时的两个日期，结果是0
        Date date1 = cn.hutool.core.date.DateUtil.parse("2024-01-01 12:00:00");
        Date date2 = cn.hutool.core.date.DateUtil.parse("2024-01-02 10:00:00");
        long betweenDay = cn.hutool.core.date.DateUtil.between(date1, date2, DateUnit.DAY);
        System.err.println(betweenDay);
        // 如果是相差时间等于24小时的两个日期，结果是1
        Date date3 = cn.hutool.core.date.DateUtil.parse("2024-01-01 12:00:00");
        Date date4 = cn.hutool.core.date.DateUtil.parse("2024-01-02 12:00:00");
        long betweenDay1 = cn.hutool.core.date.DateUtil.between(date3, date4, DateUnit.DAY);
        System.err.println(betweenDay1);

    }

    private static void test3() {
        int a = 3;
        int sum = 7;
        DecimalFormat df = new DecimalFormat("#0.00%"); //详细看DecimalFormat API，
        String format = df.format((float) a / (float) sum); //float或者double都可以
        System.err.println(format);

        //不加%后缀可以得出小数格式的百分比取值
        DecimalFormat df1 = new DecimalFormat("#0.00");
        String format1 = df1.format((float) a / (float) sum * 100) + "%";
        System.err.println(format1);

    }

    private static void test4() {
        String strDate = "2024-10-01";
        String endDate = "2024-10-31";
        List<String> list = DateUtil.getStrDatesBetween(strDate, endDate, "yyyy-MM-dd");
        list.forEach(System.err::println);
        String date = "yyyy-MM-dd HH:mm:ss";
        System.err.println(date.substring(11, 13));

    }

    private static void test5() {
        // 04
        // 03 84
        // 01
        // 02 58
        // 01 04 B0 01 07 08 00 00 01 01 07 08 00 00 00 00 00 00 01 02 58 FA 00 78 00 00 00 FA 00 78 00 00 00 FA 00 78 00 0E 10 FA 00 78 01 00 01 35 60 00 00 70 80 02 58
//        byte[] bytes = new byte[2];
//        bytes[0] = 2;
//        bytes[1] = 58;
//        long l = ((bytes[0] & 0xFFL) << 8) | (bytes[1] & 0xFFL);
//        System.err.println(l);
//        DecimalFormat df = new DecimalFormat("00,00");
//        String[] split = df.format(300).split(",");
//        System.err.println(split[0] + "====" + split[1]);
//        String hexString = Integer.toHexString(22 * 60 * 60);
//        System.err.println(hexString);
//        String s = intToHexWithZeroPadding(300, 2);
//        System.err.println(s);

//        long time = 1736324154;
//        String s = strToHex("你好");
//        System.err.println(s);
//        System.err.println(Integer.parseInt("0c", 16));
//        System.err.println(Long.parseLong("e4bda0e5a5bd", 16));
//
//        System.err.println(System.currentTimeMillis());
//        System.err.println(SystemClock.millisClock().now());

        String title = "通知推送";
        String msg = "通知推送";
        List<String> params = new ArrayList<>();
        params.add("0x40");
        params.addAll(WordUtils.byte2HexStrArray(WordUtils.convertToUtcSeconds(DateUtil.dateToString(new Date()))));
        params.addAll(WordUtils.byte2HexStrArray(RandomUtil.randomNumbers(4).getBytes()));
        params.addAll(WordUtils.splitAndAddPrefix(String.format("%02X", 0)));
        params.addAll(WordUtils.splitAndAddPrefix(String.format("%02X", 1)));
        params.addAll(WordUtils.splitAndAddPrefix(String.format("%02X", title.getBytes().length)));
        params.addAll(WordUtils.byte2HexStrArray(title.getBytes()));
        params.addAll(WordUtils.splitAndAddPrefix(String.format("%04X", msg.getBytes().length)));
        params.addAll(WordUtils.byte2HexStrArray(msg.getBytes()));
        params.addAll(WordUtils.splitAndAddPrefix(String.format("%04X", 0)));
//
        System.err.println(params);
        System.err.println("=====================");
        params.forEach(System.err::println);

        String utcTime = "2025-01-13T13:29:01Z"; // UTC时间字符串
        Instant instant = Instant.parse(utcTime); // 解析为Instant对象
        long seconds = instant.getEpochSecond(); // 获取自1970年1月1日以来的秒数
        System.out.println("UTC时间对应的秒数: " + seconds);

        Instant instant1 = DateUtil.stringToDate("2025-01-13 13:29:01", "yyyy-MM-dd HH:mm:ss").toInstant();
        long seconds1 = instant1.getEpochSecond(); // 获取自1970年1月1日以来的秒数
        System.out.println("UTC时间对应的秒数: " + seconds1);

        Date date = DateUtil.stringToDate("2025-01-13 13:29:01", "yyyy-MM-dd HH:mm:ss");
        System.out.println("UTC时间对应的秒数: " + date.getTime());
    }

    public static String strToHex(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8); // 将字符串转换为字节数组
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF); // 将字节转换为16进制
            if (hex.length() < 2) {
                hexString.append('0'); // 补零
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String intToHexWithZeroPadding(int num, int padding) {
        String hexString = Integer.toHexString(num);
        System.err.println(hexString);
        return java.lang.String.format("%" + padding + "s", hexString).replace(' ', '0');
    }


    private long readWordBytes(ByteBuf data, Integer index) {
        byte[] bytes = readBytes(data, index);
        if (bytes.length == 2) {
            return ((bytes[0] & 0xFFL) << 8) | (bytes[1] & 0xFFL);
        } else if (bytes.length == 3) {
            return ((bytes[0] & 0xFFL) << 16) | ((bytes[1] & 0xFFL) << 8) | (bytes[2] & 0xFFL);
        } else if (bytes.length == 4) {
            return ((bytes[0] & 0xFFL) << 24) | ((bytes[1] & 0xFFL) << 16) | ((bytes[2] & 0xFFL) << 8) | (bytes[3] & 0xFFL);
        } else {
            return 0;
        }
        // 这行代码将 bytes 数组中的两个字节组合成一个16位的整数 。
        // lenBytes[0] & 0xff：将第一个字节与 0xff 进行按位与操作，以确保结果在0到255之间。这是因为Java中的字节是有符号的，范围是 -128 到 127，而我们需要无符号的值。
        // << 8：将第一个字节左移8位，使其成为高8位。
        // lenBytes[1] & 0xff：同样地，将第二个字节与 0xff 进行按位与操作，确保其值在0到255之间。
        // |：使用按位或操作符将高8位和低8位组合在一起，形成一个完整的16位整数。
    }

    private byte[] readBytes(ByteBuf data, Integer index) {
        byte[] bytes = new byte[index];
        data.readBytes(bytes);
        return bytes;
    }
}
