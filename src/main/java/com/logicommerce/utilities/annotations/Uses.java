package com.logicommerce.utilities.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Uses annotation
 * @author Logicommerce
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Uses {
	Class<?> value();
}
