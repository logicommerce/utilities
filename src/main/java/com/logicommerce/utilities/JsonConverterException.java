package com.logicommerce.utilities;

import tools.jackson.core.JacksonException;

public class JsonConverterException extends Exception {

	private static final long serialVersionUID = -8957902746565895835L;

	public JsonConverterException(JacksonException exception) {
		super(exception);
	}


}
