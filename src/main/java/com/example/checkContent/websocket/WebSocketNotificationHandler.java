//package com.example.checkContent.websocket;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Service
//public class WebSocketNotificationHandler extends TextWebSocketHandler {
//
//    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        String userId = session.getAttributes().get("userId").toString();
//        sessions.put(userId, session);
//        System.out.println("Подключен пользователь: " + userId);
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        System.out.println("Получено сообщение: " + message.getPayload());
//        // Здесь можно реализовать логику обработки сообщений от клиента
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        String userId = session.getAttributes().get("userId").toString();
//        sessions.remove(userId);
//        System.out.println("Отключен пользователь: " + userId);
//    }
//
//    public void sendNotification(Long userId, String notification) {
//        WebSocketSession session = sessions.get(userId);
//        if (session != null && session.isOpen()) {
//            try {
//                session.sendMessage(new TextMessage(notification));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
