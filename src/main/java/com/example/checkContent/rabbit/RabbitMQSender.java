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
        System.out.println("(RabbitMQSender) Отправка контента на модерацию с ID: " + message.getId());
        rabbitTemplate.convertAndSend(message);
    }

    public void publishedContent(Content content) {
        System.out.println("Публикация контента: " + content.getId());
        rabbitTemplate.convertAndSend(content);
    }

}
