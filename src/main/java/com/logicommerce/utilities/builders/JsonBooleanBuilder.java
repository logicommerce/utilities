package com.logicommerce.utilities.builders;

public class JsonBooleanBuilder extends JsonValueBuilder {
	
	private final String value;
	
	public JsonBooleanBuilder(boolean value) {
		this.value = value ? "true" : "false";
	}

	@Override
	public String toString() {
		return value;
	}

}
