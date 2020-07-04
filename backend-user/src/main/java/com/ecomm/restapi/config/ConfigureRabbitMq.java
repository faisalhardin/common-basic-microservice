package com.ecomm.restapi.config;

import com.ecomm.restapi.consumer.ReceiveMessageHandler;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class ConfigureRabbitMq {

    public static final String EXCHANGE_NAME = "user.exchange";
    public static final String QUEUE_NAME_CREATE = "user.queue.create";
    public static final String QUEUE_NAME_INQUIRY = "user.queue.inquiry";
    public static final String QUEUE_NAME_UPDATE = "user.queue.update";

    @Bean
    Queue createQueueCreate() {
        return new Queue(QUEUE_NAME_CREATE, false);
    }

    @Bean
    Queue createQueueInquiry() { return new Queue(QUEUE_NAME_INQUIRY, false); }

    @Bean
    Queue createQueueUpdate() { return new Queue(QUEUE_NAME_UPDATE, false); }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding bindingCreateUser(Queue createQueueCreate, TopicExchange exchange) {
        return BindingBuilder.bind(createQueueCreate).to(exchange).with("user.post.create");
    }

    @Bean
    Binding bindingInquiryUser(Queue createQueueInquiry, TopicExchange exchange) {
        return BindingBuilder.bind(createQueueInquiry).to(exchange).with("user.get.get");
    }

    @Bean
    Binding bindingUpdateUser(Queue createQueueUpdate, TopicExchange exchange) {
        return BindingBuilder.bind(createQueueUpdate).to(exchange).with("user.post.update");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }



//    @Bean
//    SimpleMessageListenerContainer containerModify(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapterModify) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_NAME_MODIFY);
//        container.setMessageListener(listenerAdapterModify);
//        return container;
//    }


//    @Bean
//    SimpleMessageListenerContainer containerInquiry(ConnectionFactory connectionFactory,
//                                                   MessageListenerAdapter listenerAdapterInquiry) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_NAME_INQUIRY);
//        container.setMessageListener(listenerAdapterInquiry);
//        return container;
//    }

//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }

//    @Bean
//    MessageListenerAdapter listenerAdapterModify(ReceiveMessageHandler handler) {
//        return new MessageListenerAdapter(handler, "handleMessageModify");
//    }

//    @Bean
//    MessageListenerAdapter listenerAdapterInquiry(ReceiveMessageHandler handler) {
//        return new MessageListenerAdapter(handler, "handleMessageInquiry");
//    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
