package com.logicommerce.utilities.annotations;

/**
 * Helps converts types to other types
 * @author Logicommerce
 */
public interface FieldConverter<O, T> {

	T convert(O origin);

	Class<O> getOriginType();
}
