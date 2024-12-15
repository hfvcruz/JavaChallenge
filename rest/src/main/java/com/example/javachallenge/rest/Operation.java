package com.example.javachallenge.rest;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Operation {
    
    private @Id
	@GeneratedValue Long id;
	private BigDecimal inputA;
	private BigDecimal inputB;
	private BigDecimal result;

    public Operation() {}

	public Operation(BigDecimal inputA, BigDecimal inputB) {

		this.inputA = inputA;
		this.inputB = inputB;
	}

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

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Operation))
			return false;
        Operation operation = (Operation) o;
		return Objects.equals(this.inputA, operation.inputA) && Objects.equals(this.inputB, operation.inputB)
				&& Objects.equals(this.result, operation.result);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.inputA, this.inputB, this.result);
	}

	@Override
	public String toString() {
		return "Operation{" + "inputA=" + this.inputA + ", inputB='" + this.inputB + '\'' + ", result='" + this.result + '\'' + '}';
	}

}
