package com.mrdongshan.websocket.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@SpringBootApplication
public class DemoWebsocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebsocketServerApplication.class, args);
    }

}
