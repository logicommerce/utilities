package com.logicommerce.utilities;

import java.io.IOException;

public class JsonConverterException extends Exception {
	
	private static final long serialVersionUID = -8957902746565895835L;

	public JsonConverterException(IOException exception) {
		super(exception);
	}


}
