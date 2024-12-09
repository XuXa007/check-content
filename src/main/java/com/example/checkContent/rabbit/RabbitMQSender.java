package com.example.checkContent.rabbit;

import com.example.checkContent.dto.ContentModerationForMessage;
import com.example.checkContent.model.Content;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToModeration(ContentModerationForMessage message) {
        System.out.println("Отправка контента на модерацию с ID: " + message.getId());
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.exchangeName, "moder.key", message);
    }

    public void publishedContent(Content content) {
        System.out.println("Публикация контента: " + content.getId());
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.exchangeName, "publish.key", content);
    }

//    public void sendToModeration(Content content) {
//        System.out.println("Отправка сообщения на модерацию контента с ID: " + content.getId());
//        rabbitTemplate.convertAndSend(RabbitMQConfiguration.exchangeName, "moder.key", content);
//    }
//
//    public void messAddUser(Content content) {
//        System.out.println("Отправка сообщения в RabbitMQ о добавлении ID: " + content);
//        rabbitTemplate.convertAndSend(RabbitMQConfiguration.queueUserName, "user.key", content);
//    }
//
//    public void publishedContent(Content content) {
//        System.out.println("Публикация контента: " + content.getId());
//        rabbitTemplate.convertAndSend(RabbitMQConfiguration.exchangeName, "publish.key", content);
//    }
//
//    public void userCreated(Content content) {
//        System.out.println("Пользователь создан с айди: " +content.getId());
//        rabbitTemplate.convertAndSend(RabbitMQConfiguration.queueUserName, "user.key", id);
//    }




}
