package com.mrdongshan.hello.world.util;

import java.util.ArrayList;
import java.util.List;

public class WordUtils {

    // 将字节数组转换为十六进制字符串数组
    public static List<String> byte2HexStrArray(byte[] bytes) {
        List<String> hexStrList = new ArrayList<>();
        for (byte b : bytes) {
            hexStrList.add("0x" + String.format("%02X", b));
        }
        return hexStrList;
    }

    // 将当前时间转换为 UTC 秒
    public static byte[] convertToUtcSeconds(String dateString) {
        // 假设 dateString 是一个有效的日期字符串，转换为 UTC 秒
        // 这里需要实现具体的转换逻辑
        return new byte[4]; // 返回一个示例字节数组
    }

    // 将字符串分割并添加前缀
    public static List<String> splitAndAddPrefix(String str) {
        List<String> result = new ArrayList<>();
        while (str.length() >= 2) {
            str = str.substring(2);
            result.add("0x" + str.substring(0, 2));
        }
        if (str.length() == 1) {
            result.add("0x0" + str);
        }
        return result;
    }
}
