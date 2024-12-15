package com.example.javachallenge.model;

import java.math.BigDecimal;

public class CalculatorRequestDTO {
    
	private BigDecimal inputA;
	private BigDecimal inputB;
	private OperationType operationType;
	
    public BigDecimal getInputA() {
		return this.inputA;
	}
	
	public BigDecimal getInputB() {
		return this.inputB;
	}
	
	public OperationType getOperationType() {
		return operationType;
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
}
