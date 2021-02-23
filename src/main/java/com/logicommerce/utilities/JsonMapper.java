package com.logicommerce.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonMapper<T> {

	private ObjectMapper mapper;

	private Class<T> resourceClass;

	public JsonMapper(Class<T> resourceClass) {
		this.resourceClass = resourceClass;
		initMapper();
	}

	public String toJson(T object) throws JsonConverterException {
		if (object != null) {
			try {
				return mapper.writeValueAsString(object);
			} catch (JsonProcessingException exception) {
				throw new JsonConverterException(exception);
			}
		}
		return null;
	}

	public String toJson(List<T> list) throws JsonConverterException {
		if (list != null) {
			try {
				return mapper.writeValueAsString(list);
			} catch (JsonProcessingException exception) {
				throw new JsonConverterException(exception);
			}
		}
		return null;
	}

	public T fromJson(InputStream jsonStream) throws JsonConverterException {
		if (jsonStream != null) {
			try {
				return mapper.readValue(jsonStream, resourceClass);
			} catch (IOException exception) {
				throw new JsonConverterException(exception);
			}
		}
		return null;
	}

	public T fromJson(String json) throws JsonConverterException {
		if (json != null && !json.isBlank()) {
			try {
				return mapper.readValue(json, resourceClass);
			} catch (IOException exception) {
				throw new JsonConverterException(exception);
			}
		}
		return null;
	}

	private void initMapper() {
		mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.registerModule(DefaultModule.getDefaultModule());
	}
	
}
