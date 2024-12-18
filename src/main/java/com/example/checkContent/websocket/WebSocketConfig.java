//package com.example.checkContent.websocket;
//
//import com.sun.nio.sctp.NotificationHandler;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.*;
//
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    private final WebSocketNotificationHandler notificationHandler;
//
//    public WebSocketConfig(WebSocketNotificationHandler notificationHandler) {
//        this.notificationHandler = notificationHandler;
//    }
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(notificationHandler, "/ws/moderation")
//                .setAllowedOrigins("*");
//    }
//}