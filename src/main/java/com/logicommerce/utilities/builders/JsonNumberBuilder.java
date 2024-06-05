package com.logicommerce.utilities.builders;

public class JsonNumberBuilder extends JsonValueBuilder {
	
	private final Integer intValue;
	
	private final Double dobValue;

	public JsonNumberBuilder(Integer intValue) {
		dobValue = null;
		this.intValue = intValue;
	}

	public JsonNumberBuilder(Double dobValue) {
		intValue = null;
		this.dobValue = dobValue;
	}
	
	@Override
	public String toString() {
		if (intValue != null) {
			return intValue.toString();
		}
		if (dobValue != null) {
			return dobValue.toString();
		}
		return null;
	}

}
