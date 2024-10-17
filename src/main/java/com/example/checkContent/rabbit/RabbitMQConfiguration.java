package com.example.checkContent.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {
    public static final String queueContentName = "contentToModerationQueue";
    public static final String queueUserName="userQueue";
    public static final String exchangeName = "contentExchange";

    @Bean
    public Queue contentQueue() {
        return new Queue(queueContentName, true); // durable
    }

    @Bean
    public Queue userQueue() {
        return new Queue(queueUserName, true);
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding bindingContent(@Qualifier("contentQueue") Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("moder.key").noargs();

    }

    @Bean
    public Binding bindingUser(@Qualifier("userQueue") Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("user.key").noargs();
    }
}
