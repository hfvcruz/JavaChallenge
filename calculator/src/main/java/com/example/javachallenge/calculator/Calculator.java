package com.example.javachallenge.calculator;

import com.example.javachallenge.model.OperationType;
import java.math.BigDecimal;

public class Calculator {

    public static BigDecimal performCalculation(BigDecimal inputA, BigDecimal inputB, OperationType operationType) {
		switch (operationType) {
			case OperationType.SUM:
				return inputA.add(inputB);

			case OperationType.SUBTRACTION:
                return inputA.subtract(inputB);

			case OperationType.MULTIPLICATION:
                return inputA.multiply(inputB);

			case OperationType.DIVISION:
                return inputA.divide(inputB);

			default:
                return new BigDecimal(0);
		}
	}

}