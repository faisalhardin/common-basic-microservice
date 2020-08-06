package com.ecomm.product.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableRabbit
public class ConfigureRabbitMq {

    public static final String EXCHANGE_NAME = "product.exchange";
    public static final String QUEUE_NAME_CREATE = "api.product.create";
    public static final String QUEUE_NAME_INQUIRY = "api.product.inquiry";
    public static final String QUEUE_NAME_UPDATE = "api.product.update";
    public static final String QUEUE_NAME_DELETE = "api.product.delete";

    @Bean
    Queue createQueueCreate() {
        return new Queue(QUEUE_NAME_CREATE, false);
    }

    @Bean
    Queue createQueueInquiry() { return new Queue(QUEUE_NAME_INQUIRY, false); }

    @Bean
    Queue createQueueUpdate() { return new Queue(QUEUE_NAME_UPDATE, false); }

    @Bean
    Queue createQueueDelete() { return new Queue(QUEUE_NAME_DELETE, false); }

    @Bean
    TopicExchange exchange() { return new TopicExchange(EXCHANGE_NAME); }

    @Bean
    Binding bindingCreateProduct( Queue createQueueCreate, TopicExchange exchange ) {
        return BindingBuilder.bind(createQueueCreate).to(exchange).with("*.product.create");
    }

    @Bean
    Binding bindingInquiryProduct( Queue createQueueInquiry, TopicExchange exchange ) {
        return BindingBuilder.bind(createQueueInquiry).to(exchange).with("*.product.inquire");
    }

    @Bean
    Binding bindingUpdateProduct( Queue createQueueUpdate, TopicExchange exchange ) {
        return BindingBuilder.bind(createQueueUpdate).to(exchange).with("*.product.update");
    }

    @Bean
    Binding bindingDeleteProduct( Queue createQueueDelete, TopicExchange exchange ) {
        return BindingBuilder.bind(createQueueDelete).to(exchange).with("*.product.delete");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
