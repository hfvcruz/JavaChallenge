package com.example.javachallenge.rest.api;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.javachallenge.rest.producer.CalculatorRequestProducer;
import com.example.javachallenge.model.CalculatorReplyDTO;
import com.example.javachallenge.model.CalculatorRequestDTO;
import com.example.javachallenge.model.OperationType;

@RestController
public class CalculatorAPI {
    @Autowired private CalculatorRequestProducer calculatorRequestProducer;

    CalculatorAPI() {
    }

    @GetMapping("/sum")
    public CalculatorReplyDTO sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        CalculatorRequestDTO request = new CalculatorRequestDTO();
        request.setInputA(a);
        request.setInputB(b);
        request.setOperationType(OperationType.SUM);
        return calculatorRequestProducer.requestCalculation(request);
    }
   
    @GetMapping("/subtraction")
    public CalculatorReplyDTO subtraction(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        CalculatorRequestDTO request = new CalculatorRequestDTO();
        request.setInputA(a);
        request.setInputB(b);
        request.setOperationType(OperationType.SUBTRACTION);
        return calculatorRequestProducer.requestCalculation(request);
    }

    @GetMapping("/multiplication")
    public CalculatorReplyDTO multiplication (@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        CalculatorRequestDTO request = new CalculatorRequestDTO();
        request.setInputA(a);
        request.setInputB(b);
        request.setOperationType(OperationType.MULTIPLICATION);
        return calculatorRequestProducer.requestCalculation(request);
    }

    @GetMapping("/division")
    public ResponseEntity<CalculatorReplyDTO> division(@RequestParam BigDecimal a, @RequestParam BigDecimal b) throws Exception {
        if(b.equals(new BigDecimal(0))) 
        {
            // Cannot divide by 0.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        } else {
            CalculatorRequestDTO request = new CalculatorRequestDTO();
            request.setInputA(a);
            request.setInputB(b);
            request.setOperationType(OperationType.DIVISION);
            var result = calculatorRequestProducer.requestCalculation(request);
            return ResponseEntity.ok(result);
        }
    }
}
