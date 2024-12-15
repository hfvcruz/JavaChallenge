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
    }

    // @GetMapping("/sum")
    // EntityModel<Operation> sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
    //     Operation op = new Operation(a, b);
    //     BigDecimal result = a.add(b);
    //     op.setResult(result);
    //     return EntityModel.of(op, linkTo(methodOn(CalculatorController.class).sum(a, b)).withSelfRel());
    // }

    // @GetMapping("/sum")
    // BigDecimal sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
    //     return a.add(b);
    // }
    

}
