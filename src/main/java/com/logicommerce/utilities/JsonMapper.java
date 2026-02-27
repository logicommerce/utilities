package com.logicommerce.utilities;

import java.io.InputStream;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class JsonMapper<T> extends JsonMapperBase<T> {

	private Class<T> resourceClass;

	public JsonMapper(Class<T> resourceClass) {
		super();
		this.resourceClass = resourceClass;
	}

	@Override
	public T fromJson(InputStream jsonStream) throws JsonConverterException {
		if (jsonStream != null) {
			try {
				ObjectMapper mapper = mapperBuilder.build();
				return mapper.readValue(jsonStream, resourceClass);
			} catch (JacksonException exception) {
				throw new JsonConverterException(exception);
			}
		}
		return null;
	}

	@Override
	public T fromJson(String json) throws JsonConverterException {
		if (json != null && !json.isBlank()) {
			try {
				ObjectMapper mapper = mapperBuilder.build();
				return mapper.readValue(json, resourceClass);
			} catch (JacksonException exception) {
				throw new JsonConverterException(exception);
			}
		}
		return null;
	}

}
