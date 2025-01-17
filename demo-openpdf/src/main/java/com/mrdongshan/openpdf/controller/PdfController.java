package com.mrdongshan.openpdf.controller;

import com.mrdongshan.openpdf.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PdfController {

    @Autowired
    private PdfService pdfService;

    /**
     * 显示数据预览页面
     * @param model 模型数据
     * @return 模板名称
     */
    @GetMapping("/preview")
    public String preview(Model model) {
        model.addAttribute("title", "PDF文档预览");
        model.addAttribute("content", "这是一个使用Thymeleaf渲染的HTML内容，将被转换为PDF文档。");
        return "preview";
    }

    /**
     * 生成PDF文档并下载
     * @param response HTTP响应
     * @throws Exception 异常
     */
    @GetMapping("/download")
    public void downloadPdf(HttpServletResponse response) throws Exception {
        String htmlContent = pdfService.generateHtmlContent();
        pdfService.generatePdf(response, htmlContent);
    }
}