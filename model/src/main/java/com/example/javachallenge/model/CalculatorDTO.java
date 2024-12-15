package com.example.javachallenge.model;

import com.example.javachallenge.model.OperationType;
import java.math.BigDecimal;

public class CalculatorDTO {
    
    private BigDecimal inputA;
	private BigDecimal inputB;
	private OperationType operationType;
	private BigDecimal result;
	
    public BigDecimal getInputA() {
		return this.inputA;
	}
	
	public BigDecimal getInputB() {
		return this.inputB;
	}
	
	public OperationType getOperationType() {
		return operationType;
	}
	
	public BigDecimal getResult() {
		return this.result;
	}
	
	public void setInputA(BigDecimal inputA) {
		this.inputA = inputA;
	}
	
	public void setInputB(BigDecimal inputB) {
		this.inputB = inputB;
	}
	
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public void performCalculation() {
		switch (operationType) {
			case OperationType.SUM:
				result = inputA.add(inputB);
				break;

			case OperationType.SUBTRACTION:
				result = inputA.subtract(inputB);
				break;

			case OperationType.MULTIPLICATION:
				result = inputA.multiply(inputB);
				break;

			case OperationType.DIVISION:
				result = inputA.divide(inputB);
				break;

			default:
				break;
		}
	}
}
