package com.logicommerce.utilities.builders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public class QueryParamsBuilder {

	private static final String ENCODE = "UTF-8";

	private static final String PARAM_MASK = "%s=%s";

	private static final String JOIN_CHAR_PARAMS = "&";

	private Map<String, String> queryParams;

	public QueryParamsBuilder() {
		queryParams = new LinkedHashMap<>();
	}

	public QueryParamsBuilder add(String name, Object value) {
		if (name != null && value != null) {
			queryParams.put(name, value.toString());
		}
		return this;
	}

	private String setParam(Entry<String, String> entry) {
		try {
			String encodedValue = URLEncoder.encode(entry.getValue(), ENCODE);
			return String.format(PARAM_MASK, entry.getKey(), encodedValue);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return queryParams.entrySet().stream()
				.map(this::setParam)
				.filter(Objects::nonNull)
				.collect(Collectors.joining(JOIN_CHAR_PARAMS));
	}
}
