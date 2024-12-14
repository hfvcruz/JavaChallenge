package com.example.javachallenge.producer;

import com.example.javachallenge.dto.CalculatorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CalculatorRequestProducer {
    @Value("${topics.calculator.request.topic}")
    private String topicCalculatorRequest;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(CalculatorDTO pagamento) throws JsonProcessingException {
        String conteudo = objectMapper.writeValueAsString(pagamento);
        kafkaTemplate.send(topicCalculatorRequest, conteudo);
        return "Calculation sent to processing";
    }
}