module com.logicommerce.utilities {
	requires transitive java.logging;
	requires transitive com.fasterxml.jackson.core;
	requires transitive com.fasterxml.jackson.databind;
	requires transitive com.fasterxml.jackson.annotation;
	requires transitive org.bouncycastle.provider;

	exports com.logicommerce.utilities;
	exports com.logicommerce.utilities.builders;
	exports com.logicommerce.utilities.annotations;
}
