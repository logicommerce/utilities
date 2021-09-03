package com.logicommerce.utilities.annotations;

public interface FieldConverter<O, T> {

	T convert(O origin);

	Class<O> getOriginType();
}
