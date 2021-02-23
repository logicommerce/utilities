package com.logicommerce.utilities.builders;

public class JsonStringBuilder extends JsonValueBuilder {

	private final String value;

	public JsonStringBuilder(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(QUOTE);
		builder.append(value);
		builder.append(QUOTE);
		return builder.toString();
	}
}
