package com.mrdongshan.openpdf.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import javax.servlet.http.HttpServletResponse;


@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 生成HTML内容
     * @return 渲染后的HTML字符串
     */
    public String generateHtmlContent() {
        Context context = new Context();
        context.setVariable("title", "PDF文档标题");
        context.setVariable("content", 
        		"这是PDF文档的主要内容："
        		+ "“成功并不是终点，失败也不是终结，最重要的是继续前行的勇气。在人生的旅途中，我们会遇到许多挑战与挫折，这些都是成长的必经之路。每一次跌倒都是一次学习的机会，每一次失败都为成功铺设了基础。只要我们保持信念，不断努力，最终会到达梦想的彼岸。无论前方的路有多么坎坷，只要心怀希望，我们就有无限的可能性去改变自己的命运，实现心中的理想。”"
        		+ "由Thymeleaf模板引擎渲染。"
        		+ "");
        return templateEngine.process("pdf_template", context);
    }

    /**
     * 将HTML内容转换为PDF并写入响应
     * @param response HTTP响应
     * @param htmlContent HTML内容
     * @throws IOException IO异常
     * @throws DocumentException 文档异常
     */
    public void generatePdf(HttpServletResponse response, String htmlContent) throws IOException, DocumentException {
        // 设置响应类型
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=generated.pdf");

        // 创建ITextRenderer实例
        ITextRenderer renderer = new ITextRenderer();
       
        // 设置字体路径，使用 classpath 加载字体
        ITextFontResolver fontResolver = renderer.getFontResolver();
        ClassPathResource fontResource = new ClassPathResource("fonts/SimSun.ttf");
        System.out.println(fontResource.getFile().getAbsolutePath());
        fontResolver.addFont(fontResource.getFile().getAbsolutePath(), "Identity-H", true);
        
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();

        // 输出PDF到响应输出流
        try (OutputStream outputStream = response.getOutputStream()) {
            renderer.createPDF(outputStream);
            outputStream.flush();
        }
    }
}