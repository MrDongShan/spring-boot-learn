package com.mrdongshan.pdfbox.controller;

import com.mrdongshan.pdfbox.util.GenerateChartUtil;
import com.mrdongshan.pdfbox.util.JFreeChartUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class TestController {
    @GetMapping("test1")
    public void test1() throws Exception {
        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage(PDRectangle.A4);
            doc.addPage(myPage);

            String imgFileName = "1710470477175_柱状图.jpeg";
            PDImageXObject pdImage = PDImageXObject.createFromFile(imgFileName, doc);


            // 获取页面大小
            float pageWidth = myPage.getMediaBox().getWidth();
            float pageHeight = myPage.getMediaBox().getHeight();

            // 计算图片的缩放比例，使其适应页面大小
            float scaleFactor = Math.min(pageWidth / pdImage.getWidth(), pageHeight / pdImage.getHeight());

            // 设置图片的位置和大小
            float imageWidth = pdImage.getWidth() * scaleFactor;
            float imageHeight = pdImage.getHeight() * scaleFactor;
            float imageX = (pageWidth - imageWidth) / 2;
            float imageY = (pageHeight - imageHeight) / 2;


            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
                // 加载中文字体
//                InputStream resourceAsStream = TestController.class.getClassLoader().getResourceAsStream("SimHei.ttf");
//                InputStream resourceAsStream = new ClassPathResource("SimHei.ttf").getInputStream();
                File fontFile = new File(Objects.requireNonNull(this.getClass().getResource("/SimHei.ttf")).getPath());

                cont.setFont(PDType0Font.load(doc, fontFile), 24);

//                cont.setFont(PDType0Font.load(doc, resourceAsStream, false),
//                        24);


                // 将图片添加到页面上
//                cont.drawImage(pdImage, imageX, imageY, imageWidth, imageHeight);
//                cont.drawImage(pdImage, 0, 0, imageWidth, imageHeight);
                // 将文字添加到页面上
                cont.beginText();


                // 在指定的位置开启新的一行
//                cont.newLineAtOffset(25, 700);
                System.err.println("imageY：" + imageY);
                System.err.println("imageHeight：" + imageHeight);
//                cont.newLineAtOffset(25, imageY + imageHeight + 20); // 调整文本起始位置
                String line1 = "World War II (often abbreviated to WWII or WW2), "
                        + "also known as the Second World War,";
                cont.showText(line1);
                cont.newLine();
                cont.showText("想要写入一些中文,但是会报错");

                cont.newLine();

                String line2 = "was a global war that lasted from 1939 to 1945, "
                        + "although related conflicts began earlier.";
                cont.showText(line2);
                cont.newLine();

                String line3 = "It involved the vast majority of the world's "
                        + "countries—including all of the great powers—";
                cont.showText(line3);
                cont.newLine();

                String line4 = "eventually forming two opposing military "
                        + "alliances: the Allies and the Axis.";
                cont.showText(line4);
                cont.newLine();

                cont.endText();
            }

            doc.save("PDF.pdf");
        }
    }

    /**
     * 生成折线图
     */
    public static void lineChart() throws Exception {
        //x轴名称列表
        List<String> xAxisNameList = Arrays.asList("一级", "二级", "三级", "四级", "五级");
        //图例名称列表
        List<String> legendNameList = Arrays.asList("李四", "张三", "王五");
        //数据列表
        List<List<Object>> dataList = new ArrayList<>();
        dataList.add(Arrays.asList(1, 3, 5, 6, 2));
        dataList.add(Arrays.asList(2, 1, 3, 4, 5));
        dataList.add(Arrays.asList(5, 8, 4, 6, 4));

        // 返回outputStream
//        GenerateChartUtil.createLineChart(response.getOutputStream(), "各级变化图", legendNameList, xAxisNameList
//         , dataList, JFreeChartUtil.createChartTheme("宋体"), "y轴", "x轴", 600, 400);

        JFreeChart chart = GenerateChartUtil.createLineChart("各级变化图", legendNameList, xAxisNameList
                , dataList, JFreeChartUtil.createChartTheme("宋体"), "y轴", "x轴");

        // 生成折线图
        String imageName = "_折线图" + ".jpeg";
        File file = new File(imageName);

        try {
            if (file.exists()) {
                file.delete();
            }
            ChartUtils.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
