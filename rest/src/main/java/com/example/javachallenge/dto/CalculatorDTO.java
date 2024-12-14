package com.example.javachallenge.dto;

import java.math.BigDecimal;

public class CalculatorDTO {
    
    private BigDecimal inputA;
	private BigDecimal inputB;
	private BigDecimal result;

    public BigDecimal getInputA() {
		return this.inputA;
	}

	public BigDecimal getInputB() {
		return this.inputB;
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

	public void setResult(BigDecimal result) {
		this.result = result;
	}
}
