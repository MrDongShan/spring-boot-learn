package com.mrdongshan.websocket.server.controller;

import com.mrdongshan.websocket.server.service.WsServer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassDescription: 服务端请求类
 * @JdkVersion: 1.8
 */
@RestController
@RequiredArgsConstructor
public class WsServerController {
    private final WsServer wsServer;

    /**
     * 服务端发消息给客户端
     *
     * @param message 消息
     */
    @PostMapping("/send2client")
    public void send2Client(@RequestBody String message) {
//        wsServer.sendMessageToAll("this is a test for server to client");
        wsServer.sendMessageToAll(message);
    }

}
