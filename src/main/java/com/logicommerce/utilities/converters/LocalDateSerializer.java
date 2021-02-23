package com.logicommerce.utilities.converters;

import java.io.IOException;
import java.time.LocalDate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {
	
	private static final long serialVersionUID = 1022361215293479302L;

	public LocalDateSerializer() {
        super(LocalDate.class);
    }

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(LocalDateUtil.toIsoLocalDate(value));
	}
}
	
