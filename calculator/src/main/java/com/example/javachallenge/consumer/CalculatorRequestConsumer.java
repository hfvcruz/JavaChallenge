package com.example.javachallenge.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CalculatorRequestConsumer {

    @KafkaListener(
        topics = "${topics.calculator.request.topic}",
        groupId = "calculator-request-consumer-1"
    )
    public void consume(String message) {
        System.out.println("===== MENSAGEM RECEBIDA === " + message);
    }
}