package com.logicommerce.utilities.builders;

public abstract class JsonValueBuilder {

	protected static final String COMMA = ",";
	
	protected static final String COLON = ":";
	
	protected static final String OPEN_BRACE = "{";
	
	protected static final String CLOSE_BRACE = "}";
	
	protected static final String OPEN_BRACKET = "[";
	
	protected static final String CLOSE_BRACKET = "]";
	
	protected static final String QUOTE = "\"";

	protected void validateValue(Object value) {
		if (value == null)
			throw new NullPointerException("Value can not be null");
	}

	protected void validateName(String name) {
		if (name == null)
			throw new NullPointerException("Name can not be null");
	}

	@Override
	public abstract String toString();

}
