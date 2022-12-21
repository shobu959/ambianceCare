package com.stackroute.customerservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageRescheduleConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRescheduleConfiguration.class);
    public static final String BOOKING_QUEUE = "booking_queue";
    public static final String RESCHEDULE_QUEUE = "reschedule_queue";
    public static final String CANCEL_QUEUE = "cancel_queue";
    public static final String CART_TOTAL_AMOUNT_QUEUE = "cart_queue";
    public static final String MESSAGE_EXCHANGE = "message_exchange";
    public static final String ROUTING_KEY = "booking_routingKey";
    public static final String RESCHEDULE_ROUTING_KEY = "reschedule_message_routingKey";
    public static final String CANCEL_ROUTING_KEY = "cancel_message_routingKey";
    public static final String CART_ROUTING_KEY = "cart_routingKey";
    public static final String RESCHEDULE_MESSAGE_EXCHANGE = "reschedule_message_exchange";
    public static final String CANCEL_MESSAGE_EXCHANGE = "cancel_message_exchange";


    @Bean
    public Queue rescheduleQueue() {
        return new Queue(RESCHEDULE_QUEUE);
    }

    @Bean
    public Queue bookingQueue() {
        return new Queue(BOOKING_QUEUE);
    }

    @Bean
    public Queue cancelQueue() {
        return new Queue(CANCEL_QUEUE);
    }

    @Bean
    public Queue cartQueue() {
        return new Queue(CART_TOTAL_AMOUNT_QUEUE);
    }

    @Bean
    public TopicExchange messageExchange() {
        return new TopicExchange(MESSAGE_EXCHANGE);
    }

    @Bean
    public Binding rescheduleBinding(Queue rescheduleQueue, TopicExchange exchange) {
        return BindingBuilder.bind(rescheduleQueue).to(exchange).with(RESCHEDULE_ROUTING_KEY);
    }

    @Bean
    public Binding bookingBinding(Queue bookingQueue, TopicExchange exchange) {
        return BindingBuilder.bind(bookingQueue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding cancelBinding(Queue cancelQueue, TopicExchange exchange) {
        return BindingBuilder.bind(cancelQueue).to(exchange).with(CANCEL_ROUTING_KEY);
    }

    @Bean
    public Binding cartBinding(Queue cartQueue, TopicExchange exchange) {
        return BindingBuilder.bind(cartQueue).to(exchange).with(CART_ROUTING_KEY);
    }

    @Bean
    public MessageConverter rescheduleMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rescheduleTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(rescheduleMessageConverter());
        return rabbitTemplate;
    }
}
