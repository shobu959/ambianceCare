package com.stackroute.expertservice.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitMQDomain.ExpertAvailabilityDTO;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private FanoutExchange exchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, FanoutExchange exchange){
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessageToRabbitMq(ExpertAvailabilityDTO expertAvailabilityDTO){
        rabbitTemplate.convertAndSend(exchange.getName(), "", expertAvailabilityDTO);
    }
}
