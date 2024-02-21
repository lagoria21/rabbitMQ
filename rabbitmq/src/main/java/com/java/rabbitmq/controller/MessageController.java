package com.java.rabbitmq.controller;


import com.java.rabbitmq.dto.User;
import com.java.rabbitmq.publisher.RabbitMQJsonProducer;
import com.java.rabbitmq.publisher.RabbitMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
    private final RabbitMQProducer producer;
    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    public MessageController(RabbitMQProducer producer, RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.producer = producer;
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        LOGGER.info(String.format("Received message -> %s", message));
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMq ....");
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessageJson(@RequestBody User user){
        LOGGER.info(String.format("Received message -> %s", user.toString()));
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent to RabbitMq format Json ... ");
    }
}
