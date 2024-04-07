package com.mrdongshan.aspose.util;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.nio.file.Files;

@Slf4j
public class WordsToPDFUtil {
    /**
     * 输出到指定的目录
     */
    public static void wordToPdf(String inPath, String outPath) throws Exception {
        Document doc = null;
        //去水印
        removeWaterMark();
        try (InputStream is = Files.newInputStream(new File(inPath).toPath());
             FileOutputStream os = new FileOutputStream(outPath);) {

            doc = new Document(is);
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wordToPdf(InputStream inputStream, String outPath) throws Exception {
        Document doc = null;
        //去水印
        removeWaterMark();
        try (InputStream is = inputStream;
             FileOutputStream os = new FileOutputStream(outPath);) {

            doc = new Document(is);
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回byte数组
     *
     * @param is
     */
    public static byte[] wordToPdf(InputStream is) throws Exception {
        Document doc = null;
        //去水印
        removeWaterMark();
        try {
            // 创建一个字节数组输出流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            doc = new Document(is);
            doc.save(outputStream, SaveFormat.PDF);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 去除水印
     * 使用反射替换变量
     *
     * @return
     */
    private static void removeWaterMark() throws Exception {
        Class<?> aClass = Class.forName("com.aspose.words.zzXyu");
        java.lang.reflect.Field zzZXG = aClass.getDeclaredField("zzZXG");
        zzZXG.setAccessible(true);
        java.lang.reflect.Field modifiersField = zzZXG.getClass().getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(zzZXG, zzZXG.getModifiers() & ~Modifier.FINAL);
        zzZXG.set(null, new byte[]{76, 73, 67, 69, 78, 83, 69, 68});
    }
}

