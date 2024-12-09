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
    public static final String queueContentName = "contentToModerationQueue";
    public static final String exchangeName = "contentExchange";

    @Bean
    public Queue contentQueue() {
        return new Queue(queueContentName, true);
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue contentQueue, Exchange exchange) {
        return BindingBuilder
                .bind(contentQueue)
                .to(exchange)
                .with("moder.key")
                .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
//    @Bean
//    public Binding bindingUser(@Qualifier("userQueue") Queue queue, Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("user.key").noargs();
//    }
//
//    @Bean
//    public Binding bindingUser(@Qualifier("userQueue") Queue queue, Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("user.key").noargs();
//    }
//
//    @Bean
//    public Binding bindingPublished(@Qualifier("publishedQueue") Queue queue, Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("moder.key").noargs();
//
//    }
}
