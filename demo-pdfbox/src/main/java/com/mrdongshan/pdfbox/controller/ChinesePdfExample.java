package com.mrdongshan.pdfbox.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 两个问题：
 * 1.可以不指定位置添加图片或者文字吗，按照我的添加顺序排列即可
 * 2.如果页面的图片或者文字过多可以自动放到下一页吗，类似word的那种
 */
//https://blog.csdn.net/jamenu/article/details/124009466
//https://geek-docs.com/java/java-tutorial/pdfbox.html#ftoc-heading-3
//https://www.yiibai.com/pdfbox/pdfbox_adding_rectangles.html
@RestController
public class ChinesePdfExample {

    @GetMapping("test2")
    public void adm() {
        try (// 创建一个新的PDF文档
             PDDocument document = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // 创建一个内容流，用于在页面上绘制文本、图片等
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page);) {

                // 添加文字
//                addText(contentStream, document);
                // 添加图片
//                addImg(document, page, contentStream);
                // 尝试处理图片和文字的位置和排序
//                addText1(contentStream, document);

                // 关闭内容流并保存PDF文档
                document.save("ChinesePdfExample.pdf");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void addText1(PDPageContentStream contentStream, PDDocument document) throws IOException {
        // 加载要插入的图片
        PDImageXObject image = PDImageXObject.createFromFile("1710470477175_柱状图.jpeg", document);

        // 在页面上添加文本（不指定位置）
        contentStream.beginText();

        // 设置字体和字号
        URL fontUrl = this.getClass().getResource("/SimHei.ttf");
        if (fontUrl != null) {
            File fontFile = new File(fontUrl.getPath());
            contentStream.setFont(PDType0Font.load(document, fontFile), 24);
        } else {
            System.err.println("字体文件未找到");
        }

        contentStream.showText("Hello, World!");
        contentStream.endText();

        // 在页面上添加图片（不指定位置）
        contentStream.drawImage(image, 50, 50);

    }


    private void addText(PDPageContentStream contentStream, PDDocument document) throws IOException {
        // 设置字体和字号
        URL fontUrl = this.getClass().getResource("/SimHei.ttf");
        if (fontUrl != null) {
            File fontFile = new File(fontUrl.getPath());
            contentStream.setFont(PDType0Font.load(document, fontFile), 24);
        } else {
            System.err.println("字体文件未找到");
        }

        // 设置文本的位置
        contentStream.beginText();
        contentStream.newLineAtOffset(25, 700);

        // 添加中文文本
        String text = "这是一个包含中文的PDF文件";
        contentStream.showText(text);

        // 结束文本绘制
        contentStream.endText();
    }

    private void addImg(PDDocument doc, PDPage page, PDPageContentStream cont) throws IOException {
        String imgFileName = "1710470477175_柱状图.jpeg";
        PDImageXObject pdImage = PDImageXObject.createFromFile(imgFileName, doc);

        // 获取页面大小
        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();

        // 计算图片的缩放比例，使其适应页面大小
        float scaleFactor = Math.min(pageWidth / pdImage.getWidth(), pageHeight / pdImage.getHeight());

        // 设置图片的位置和大小
        float imageWidth = pdImage.getWidth() * scaleFactor;
        float imageHeight = pdImage.getHeight() * scaleFactor;
        // 设置水平和垂直居中？
        float imageX = (pageWidth - imageWidth) / 2;
        float imageY = (pageHeight - imageHeight) / 2;

        // 将图片添加到页面上
        cont.drawImage(pdImage, imageX, imageY, imageWidth, imageHeight);


    }
}
