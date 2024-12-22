package com.example.checkContent.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {
    public static final String CONTENT_MODERATION_REQUEST_QUEUE = "content_moderation_request";
    public static final String CONTENT_MODERATION_RESULT_QUEUE = "content_moderation_result";
    public static final String NOTIFICATION_QUEUE = "content_notification";

    @Bean
    public Queue content_request_queue() {
        return new Queue(CONTENT_MODERATION_REQUEST_QUEUE, true);
    }

    @Bean
    public Queue content_result_queue() {
        return new Queue(CONTENT_MODERATION_RESULT_QUEUE, true);
    }

    @Bean
    public Queue notification_queue() {
        return new Queue(NOTIFICATION_QUEUE, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }
}
