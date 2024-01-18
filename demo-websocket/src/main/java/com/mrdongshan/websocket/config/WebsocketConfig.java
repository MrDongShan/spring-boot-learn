package com.mrdongshan.websocket.config;

import com.mrdongshan.websocket.service.HttpAuthHandler;
import com.mrdongshan.websocket.service.MyInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketConfigurer {

    private final HttpAuthHandler httpAuthHandler;
    private final MyInterceptor myInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(httpAuthHandler, "myWS") // ws://127.0.0.1:8080/myWS
                .addInterceptors(myInterceptor)
                .setAllowedOrigins("*");
    }

}

