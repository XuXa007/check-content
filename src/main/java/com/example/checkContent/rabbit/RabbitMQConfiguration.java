package com.example.checkContent.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    public static final String contentToModerationQueue = "contentToModerationQueue";
    public static final String publishQueue = "contentPublishQueue";
    public static final String exchangeName = "contentExchange";

    @Bean
    public Queue moderationQueue() {
        return new Queue(contentToModerationQueue, true);
    }

    @Bean
    public Queue publishQueue() {
        return new Queue(publishQueue, true);
    }

    @Bean
    public Queue moderationResultQueue() {
        return new Queue("moderation.results", true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding resultBinding() {
        return BindingBuilder
                .bind(moderationResultQueue())
                .to(exchange())
                .with("moderation.result");
    }

    @Bean
    public Binding moderationBinding(Queue moderationQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(moderationQueue)
                .to(exchange)
                .with("moder.key");
    }

    @Bean
    public Binding publishBinding(Queue publishQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(publishQueue)
                .to(exchange)
                .with("publish.key");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
