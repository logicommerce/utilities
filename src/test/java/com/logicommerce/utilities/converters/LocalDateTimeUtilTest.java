package com.logicommerce.utilities.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class LocalDateTimeUtilTest {

	@Test
	void testParse8601() {
		assertEquals(
			LocalDateTime.of(2024, 1, 25, 12, 34, 56),
			LocalDateTimeUtil.parse8601("2024-01-25T12:34:56Z"));
		assertEquals(
			LocalDateTime.of(2024, 1, 25, 12, 34, 56),
			LocalDateTimeUtil.parse8601("2024-01-25T12:34:56+01:00"));
		assertEquals(
			LocalDateTime.of(2024, 1, 25, 12, 34, 56, 789000000),
			LocalDateTimeUtil.parse8601("2024-01-25T12:34:56.789Z")
		);
	}

	@Test
	void testToIso8601() {
		assertEquals("", LocalDateTimeUtil.toIso8601(null));
		assertEquals(
			"2024-01-25T12:34:56Z",
			LocalDateTimeUtil.toIso8601(LocalDateTime.of(2024, 1, 25, 12, 34, 56)));
		assertEquals(
			"2024-01-25T12:34:56Z",
			LocalDateTimeUtil.toIso8601(LocalDateTime.of(2024, 1, 25, 12, 34, 56, 789000000)));
	}
}
