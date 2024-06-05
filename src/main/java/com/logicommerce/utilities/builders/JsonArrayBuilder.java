package com.logicommerce.utilities.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonArrayBuilder extends JsonValueBuilder {
	
	private List<JsonValueBuilder> values = new ArrayList<>();

	public JsonArrayBuilder add(JsonBuilder value) {
		validateValue(value);
		values.add(value);
		return this;
	}

	public JsonArrayBuilder add(String value) {
		validateValue(value);
		values.add(new JsonStringBuilder(value));
		return this;
	}

	public JsonArrayBuilder add(Integer value) {
		validateValue(value);
		values.add(new JsonNumberBuilder(value));
		return this;
	}

	public JsonArrayBuilder add(Double value) {
		validateValue(value);
		values.add(new JsonNumberBuilder(value));
		return this;
	}

	public JsonArrayBuilder add(int value) {
		values.add(new JsonNumberBuilder(value));
		return this;
	}

	public JsonArrayBuilder add(double value) {
		values.add(new JsonNumberBuilder(value));
		return this;
	}

	public JsonArrayBuilder add(boolean value) {
		values.add(new JsonBooleanBuilder(value));
		return this;
	}

	public JsonArrayBuilder add(JsonArrayBuilder value) {
		validateValue(value);
		values.add(value);
		return this;
	}
	
	public JsonArrayBuilder addAll(List<JsonValueBuilder> values) {
		this.values.addAll(values);
		return this;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(OPEN_BRACKET)
				.append(values.stream()
							.map(JsonValueBuilder::toString)
							.collect(Collectors.joining(COMMA)))
				.append(CLOSE_BRACKET).toString();
	}

}
