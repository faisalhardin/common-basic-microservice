package com.ecomm.productapi.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectReplyToMessageListenerContainer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.impl.;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class ConfigureRabbitMq {

    @Autowired
    public ConnectionFactory connectionFactory;

    public static final String EXCHANGE_NAME = "product.exchange";
    public static final String QUEUE_NAME_CREATE = "api.product.create";
    public static final String QUEUE_NAME_INQUIRY = "api.product.inquiry";
    public static final String QUEUE_NAME_UPDATE = "api.product.update";
    public static final String QUEUE_NAME_DELETE = "api.product.delete";

//    @Bean
//    Queue createQueueCreate() {
//        return new Queue(QUEUE_NAME_CREATE, false);
//    }
//
//    @Bean
//    Queue createQueueInquiry() { return new Queue(QUEUE_NAME_INQUIRY, false); }
//
//    @Bean
//    Queue createQueueUpdate() { return new Queue(QUEUE_NAME_UPDATE, false); }
//
//    @Bean
//    Queue createQueueDelete() { return new Queue(QUEUE_NAME_DELETE, false); }
//
//    @Bean
//    TopicExchange exchange() { return new TopicExchange(EXCHANGE_NAME); }
//
//    @Bean
//    Binding bindingCreateProduct( Queue createQueueCreate, TopicExchange exchange ) {
//        return BindingBuilder.bind(createQueueCreate).to(exchange).with("*.product.create");
//    }
//
//    @Bean
//    Binding bindingInquiryProduct( Queue createQueueInquiry, TopicExchange exchange ) {
//        return BindingBuilder.bind(createQueueInquiry).to(exchange).with("*.product.inquire");
//    }
//
//    @Bean
//    Binding bindingUpdateProduct( Queue createQueueUpdate, TopicExchange exchange ) {
//        return BindingBuilder.bind(createQueueUpdate).to(exchange).with("*.product.update");
//    }
//
//    @Bean
//    Binding bindingDeleteProduct( Queue createQueueDelete, TopicExchange exchange ) {
//        return BindingBuilder.bind(createQueueDelete).to(exchange).with("*.product.delete");
//    }



    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
//    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
//        rabbitTemplate.receive(replyQueue().getName());
////        rabbitTemplate.setReplyAddress(replyQueue().getName());
//        rabbitTemplate.setUseDirectReplyToContainer(false);
//        return rabbitTemplate;
//    }

//    @Bean
//    public DirectReplyToMessageListenerContainer directReplyToMessageListenerContainer(final ConnectionFactory connectionFactory, RabbitTemplate rabbitTemplate) {
//
//        DirectReplyToMessageListenerContainer container = new DirectReplyToMessageListenerContainer(connectionFactory);
//        container.setConnectionFactory(connectionFactory);
//        container.setMessageListener(rabbitTemplate);
//        container.setQueues(replyQueue());
//        return container;
//    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
//        rabbitTemplate.setReplyAddress(replyQueue().getName());
//        rabbitTemplate.setUseDirectReplyToContainer(false);
        return rabbitTemplate;
    }

//    @Bean
//    public Queue replyQueue() {
//        return new Queue("my.reply.queue");
//    }


}
