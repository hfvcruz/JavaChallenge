package com.example.javachallenge.calculator.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.javachallenge.model.CalculatorDTO;

@Component  
public class CalculatorRequestConsumer {

    @KafkaListener(topics = "${kafka.topic.request-topic}")
    @SendTo
    public CalculatorDTO consume(CalculatorDTO message) throws InterruptedException {
        System.out.println("===== MENSAGEM RECEBIDA === ");
        System.out.println("A=" + message.getInputA().toString() + 
        " B=" + message.getInputB().toString() + 
        " OperationType=" + message.getOperationType().toString());

        message.performCalculation();

        System.out.println("Result=" + message.getResult().toString());

        // int sum = request.getFirstNumber() + request.getSecondNumber();
		//  request.setAdditionalProperty("sum", sum);
		return message;
    }
}