package com.mrdongshan.websocket.client.controller;

import lombok.RequiredArgsConstructor;
import org.java_websocket.client.WebSocketClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassDescription: 客户端请求类
 * @JdkVersion: 1.8
 */
@RestController
@RequiredArgsConstructor
public class WsClientController {
    private final WebSocketClient wsClient;

    /**
     * 客户端发消息给服务端
     *
     * @param message
     */
    @PostMapping("/send2server")
    public void websocket(@RequestBody String message) {
//        wsClient.send("test for client to server");
        wsClient.send(message);
    }

    /**
     * 客户端发消息给服务端
     *
     */
    @GetMapping("/test/send2server")
    public void testWebsocket() {
        wsClient.send("test for client to server");
    }

}

