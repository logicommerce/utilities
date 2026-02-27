package com.logicommerce.utilities;

import java.io.InputStream;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class JsonMapperRef<T> extends JsonMapperBase<T> {

	private TypeReference<T> typeReference;

	public JsonMapperRef(TypeReference<T> typeReference) {
		super();
		this.typeReference = typeReference;
	}

	@Override
	public T fromJson(InputStream jsonStream) throws JsonConverterException {
		if (jsonStream != null) {
			try {
				ObjectMapper mapper = mapperBuilder.build();
				return mapper.readValue(jsonStream, typeReference);
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
				return mapper.readValue(json, typeReference);
			} catch (JacksonException exception) {
				throw new JsonConverterException(exception);
			}
		}
		return null;
	}
}
