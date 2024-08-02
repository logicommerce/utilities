package com.logicommerce.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper.Builder;

public class JsonMapper<T> {

	private Class<T> resourceClass;

	private Builder mapperBuilder;

	public JsonMapper(Class<T> resourceClass) {
		this.resourceClass = resourceClass;
		initMapper();
	}

	public String toJson(T object) throws JsonConverterException {
		if (object != null) {
			try {
				ObjectMapper mapper = mapperBuilder.build();
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
				ObjectMapper mapper = mapperBuilder.build();
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
				ObjectMapper mapper = mapperBuilder.build();
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
				ObjectMapper mapper = mapperBuilder.build();
				return mapper.readValue(json, resourceClass);
			} catch (IOException exception) {
				throw new JsonConverterException(exception);
			}
		}
		return null;
	}
	
	public void setAcceptCaseInsentiveEnums(boolean enable) {
		if (enable) {
			mapperBuilder.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
		} else {
			mapperBuilder.disable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
		}
	}
	
	public void setUnwrapRoot(boolean enable) {
		if (enable) {
			mapperBuilder.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		} else {
			mapperBuilder.disable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		}
	}

	private void initMapper() {
		mapperBuilder = com.fasterxml.jackson.databind.json.JsonMapper.builder();
		mapperBuilder.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		mapperBuilder.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapperBuilder.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapperBuilder.addModule(DefaultModule.getDefaultModule());
	}
	
}
