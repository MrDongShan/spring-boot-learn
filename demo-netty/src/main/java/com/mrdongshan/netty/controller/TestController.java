package com.mrdongshan.netty.controller;

import com.mrdongshan.netty.service.TcpClientService;
import com.mrdongshan.netty.service.TcpServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TcpServiceService tcpServiceService;
    private final TcpClientService tcpClientService;

    @GetMapping("start/tcp/service/demo1")
    public void startTcpService() {
        //先启动服务端，再启动客户端
        tcpServiceService.createTcpServiceDemo1();
    }

    @GetMapping("start/tcp/client/demo1")
    public void startTcpClient() {
        tcpClientService.createTcpClientDemo1();
    }


}
