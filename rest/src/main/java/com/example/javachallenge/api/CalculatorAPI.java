package com.example.javachallenge.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.javachallenge.Operation;
import com.example.javachallenge.dto.CalculatorDTO;
import com.example.javachallenge.service.CalculatorService;

@RestController
public class CalculatorAPI {
    @Autowired private CalculatorService calculatorService;

    CalculatorAPI() {
    }

    @GetMapping("/employees")
    String all() {
        return "All employees returned :)";
    }

    @GetMapping("/sum")
    public String sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        CalculatorDTO cal = new CalculatorDTO();
        cal.setInputA(a);
        cal.setInputB(b);
        return calculatorService.requestCalculation(cal);
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
