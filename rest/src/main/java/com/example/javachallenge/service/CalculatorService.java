package com.example.javachallenge.service;

import com.example.javachallenge.dto.CalculatorDTO;
import com.example.javachallenge.producer.CalculatorRequestProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    @Autowired private CalculatorRequestProducer calculatorRequestProducer;

    public String requestCalculation (CalculatorDTO calculator) {
        try {
            return calculatorRequestProducer.sendMessage(calculator);
        } catch (JsonProcessingException e) {
            return "Error while request calculation "+ e.getMessage();
        }
    }
}