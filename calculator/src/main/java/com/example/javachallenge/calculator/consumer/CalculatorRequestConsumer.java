package com.example.javachallenge.calculator.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.example.javachallenge.calculator.Calculator;
import com.example.javachallenge.model.CalculatorRequestDTO;
import com.example.javachallenge.model.CalculatorReplyDTO;

@Component  
public class CalculatorRequestConsumer {

    @KafkaListener(topics = "${kafka.topic.request-topic}",
                   groupId = "calculator-request-consumer")
    @SendTo
    public CalculatorReplyDTO consume(CalculatorRequestDTO request) throws InterruptedException {
        System.out.println("===== CALCULATION REQUEST RECEIVED === ");
        System.out.println("A=" + request.getInputA().toString() + 
        " B=" + request.getInputB().toString() + 
        " OperationType=" + request.getOperationType().toString());

        var result = Calculator.performCalculation(request.getInputA(),  request.getInputB(), request.getOperationType());

        System.out.println("Result=" + result.toString());

        var reply = new CalculatorReplyDTO();
        reply.setResult(result);
		return reply;
    }
}