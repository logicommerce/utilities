package com.logicommerce.utilities;

import java.io.InputStream;
import java.util.List;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper.Builder;

public abstract class JsonMapperBase<T> {

	protected Builder mapperBuilder;

	JsonMapperBase() {
		initMapper();
	}

	abstract T fromJson(InputStream jsonStream) throws JsonConverterException;

	abstract T fromJson(String json) throws JsonConverterException;

	public String toJson(T object) throws JsonConverterException {
		if (object != null) {
			try {
				ObjectMapper mapper = mapperBuilder.build();
				return mapper.writeValueAsString(object);
			} catch (JacksonException exception) {
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
			} catch (JacksonException exception) {
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
		mapperBuilder = tools.jackson.databind.json.JsonMapper.builder();
		mapperBuilder.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		mapperBuilder.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapperBuilder.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapperBuilder.addModule(DefaultModule.getDefaultModule());
	}

}

