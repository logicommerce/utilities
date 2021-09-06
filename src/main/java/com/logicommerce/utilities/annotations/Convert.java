package com.logicommerce.utilities.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Converter annotation. Uses FieldConverter interface
 * @author Logicommerce
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Convert {
	Class<? extends FieldConverter<?, ?>> converter();
}
