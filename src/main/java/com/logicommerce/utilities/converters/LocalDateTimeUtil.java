package com.logicommerce.utilities.converters;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LocalDateTimeUtil {

	private static List<String> possiblePatterns = Arrays.asList("yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss",
			"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyyMMddHHmm");

	private LocalDateTimeUtil() {}

	public static LocalDateTime toLocalDateTime(String value) {
		for (String pattern : possiblePatterns) {
			try {
				LocalDateTime localDateTime = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
				if (localDateTime != null) {
					return localDateTime;
				}
			} catch (DateTimeParseException ex) { // pass
			}
		}
		try {
			return parse8601(value);
		} catch (DateTimeParseException ex) { // pass
		}
		LocalDate localDate = LocalDateUtil.toLocalDate(value);
		if (localDate != null) {
			return LocalDateTime.of(localDate, LocalTime.of(0, 0));
		}
		return null;
	}

	public static LocalDateTime toLocalDateTime(int year, int month, int day, int hour, int minute, int second) {
		return LocalDateTime.of(year, month, day, hour, minute, second);
	}

	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTimeUtil.toLocalDateTime(date.toInstant());
	}

	private static LocalDateTime toLocalDateTime(Instant instant) {
		return instant.atOffset(ZoneOffset.UTC)
				.toLocalDateTime();
	}

	public static LocalDateTime toLocalDateTime(int year, int month, int day) {
		return LocalDateTime.of(year, month, day, 0, 0, 0);
	}


	public static LocalDateTime parse8601(String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		return LocalDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}

	public static String toIso8601(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return "";
		}
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		LocalDateTime dateTime = localDateTime.truncatedTo(ChronoUnit.SECONDS);
		if (dateTime.isSupported(ChronoField.OFFSET_SECONDS)) {
			// TODO Review TimeZone general settings when save objects with dates
			return dateTimeFormatter.format(dateTime);
		} else {
			return dateTimeFormatter.format(dateTime.atZone(ZoneOffset.UTC));
		}
	}
}
