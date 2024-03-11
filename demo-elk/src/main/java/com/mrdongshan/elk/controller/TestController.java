package com.mrdongshan.elk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping("start")
    public void start() {
        log.info("test-start");
    }

    @GetMapping("/elk")
    public void index() {
        String message = "logback ELK成功接入了，时间：" + new Date();
        log.info(message);
    }
}
