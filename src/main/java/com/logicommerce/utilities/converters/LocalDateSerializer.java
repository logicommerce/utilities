package com.logicommerce.utilities.converters;

import java.time.LocalDate;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

	private static final long serialVersionUID = 1022361215293479302L;

	public LocalDateSerializer() {
		super(LocalDate.class);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
		gen.writeString(LocalDateUtil.toIsoLocalDate(value));
	}
}

