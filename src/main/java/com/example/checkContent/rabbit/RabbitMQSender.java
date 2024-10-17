package com.example.checkContent.rabbit;

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

    public void sendToModeration(Long contentId) {
        System.out.println("Отправка сообщения в RabbitMQ о контенте ID: " + contentId);

        rabbitTemplate.convertAndSend(RabbitMQConfiguration.exchangeName, "moder.key", contentId);
    }

    public void messAddUser(String username) {
        System.out.println("Отправка сообщения в RabbitMQ о добавлении ID: " + username);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.queueUserName, "user.key", username);
    }

}
