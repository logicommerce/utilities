package com.logicommerce.utilities.builders;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class JsonBuilder extends JsonValueBuilder {

	private Map<JsonValueBuilder, JsonValueBuilder> valueMap;
	
	public JsonBuilder() {
		valueMap = new LinkedHashMap<>();
	}

	public JsonBuilder add(String name, String value) {
		validateName(name);
		validateValue(value);
		putValueMap(new JsonStringBuilder(name), new JsonStringBuilder(value));
		return this;
	}

	public JsonBuilder add(String name, Integer value) {
		validateName(name);
		validateValue(value);
		putValueMap(new JsonStringBuilder(name), new JsonNumberBuilder(value));
		return this;
	}

	public JsonBuilder add(String name, Double value) {
		validateName(name);
		validateValue(value);
		putValueMap(new JsonStringBuilder(name), new JsonNumberBuilder(value));
		return this;
	}

	public JsonBuilder add(String name, int value) {
		validateName(name);
		putValueMap(new JsonStringBuilder(name), new JsonNumberBuilder(value));
		return this;
	}

	public JsonBuilder add(String name, double value) {
		validateName(name);
		putValueMap(new JsonStringBuilder(name), new JsonNumberBuilder(value));
		return this;
	}

	public JsonBuilder add(String name, boolean value) {
		validateName(name);
		putValueMap(new JsonStringBuilder(name), new JsonBooleanBuilder(value));
		return this;
	}

	public JsonBuilder add(String name, JsonBuilder value) {
		validateName(name);
		validateValue(value);
		putValueMap(new JsonStringBuilder(name), value);
		return this;
	}

	public JsonBuilder add(String name, JsonArrayBuilder value) {
		validateName(name);
		validateValue(value);
		putValueMap(new JsonStringBuilder(name), value);
		return this;
	}

	public JsonBuilder addNull(String name) {
		validateName(name);
		putValueMap(new JsonStringBuilder(name), new JsonNullBuilder());
		return this;
	}

	private void putValueMap(JsonValueBuilder name, JsonValueBuilder value) {
		valueMap.put(name, value);
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append(OPEN_BRACE)
				.append(valueMap.entrySet().stream()
							.map(this::getLine)
							.collect(Collectors.joining(COMMA)))
				.append(CLOSE_BRACE).toString();
	}
	
	private String getLine(Entry<JsonValueBuilder, JsonValueBuilder> entry) {
		return new StringBuilder()
				.append(entry.getKey().toString())
				.append(COLON)
				.append(entry.getValue().toString())
				.toString();
	}

}
