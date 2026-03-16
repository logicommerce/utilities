package com.logicommerce.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.logicommerce.utilities.converters.LocalDateDeserializer;
import com.logicommerce.utilities.converters.LocalDateSerializer;
import com.logicommerce.utilities.converters.LocalDateTimeDeserializer;
import com.logicommerce.utilities.converters.LocalDateTimeSerializer;

public class DefaultModule {
	private DefaultModule() {}
	
	public static SimpleModule getDefaultModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(LocalDate.class, new LocalDateSerializer());
		module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
		module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
		module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
		return module;

	}

}
