package com.example.javachallenge.rest.service;

import com.example.javachallenge.model.CalculatorDTO;
import com.example.javachallenge.rest.producer.CalculatorRequestProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    @Autowired private CalculatorRequestProducer calculatorRequestProducer;

    public CalculatorDTO requestCalculation (CalculatorDTO calculator) {
        try {
            return calculatorRequestProducer.sendMessage(calculator);

        } catch (Exception e) {
            System.out.println("Error while request calculation " + e.getMessage());
            return null;
        }
    }
}