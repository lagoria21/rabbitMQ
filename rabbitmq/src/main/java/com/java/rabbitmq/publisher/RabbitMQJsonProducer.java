package com.java.rabbitmq.publisher;

import com.java.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Value("${rabbit.exchange.name}")
    private String exchange;

    @Value("${rabbit.routing.json.key}")
    private String routingJsonKey;


    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("Json message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange,routingJsonKey, user);
    }
}
