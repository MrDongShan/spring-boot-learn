package com.mrdongshan.aspose.controller;

import com.mrdongshan.aspose.util.WordsToPDFUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

/**
 * 仅适用于 21.6 版本 (jar包下载地址：https://releases.aspose.com/java/repo/com/aspose/aspose-words/21.6/)
 * https://blog.csdn.net/qq_42785250/article/details/131325432
 */
@RestController
public class TestController {
    @GetMapping("test")
    private void test() throws Exception {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Example.doc");

//        String inPath = Objects.requireNonNull(this.getClass().getResource("Example.doc")).getPath();
        String outPath = "output.pdf";
        WordsToPDFUtil.wordToPdf(inputStream, outPath);
    }


}
