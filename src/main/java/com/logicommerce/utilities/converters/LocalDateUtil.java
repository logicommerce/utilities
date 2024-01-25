package com.logicommerce.utilities.converters;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LocalDateUtil {

	private static List<String> possiblePatterns = Arrays.asList("yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd");

	private LocalDateUtil() {}

	public static String toIsoLocalDate(LocalDate localDate) {
		if (localDate == null) {
			return "";
		}
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		return dateTimeFormatter.format(localDate);
	}

	public static LocalDate toLocalDate(String value) {
		if (value == null) {
			return null;
		}
		DateTimeParseException exception = null;
		for (String format : possiblePatterns) {
			try {
				LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(format));
				if (localDate != null) {
					return localDate;
				}
			} catch (DateTimeParseException ex) {
				exception = ex;
			}
		}
		if (exception != null) {
			throw exception;
		}
		return null;
	}

	public static LocalDate toLocalDate(Date date) {
		return LocalDateUtil.toLocalDate(date.toInstant());
	}

	public static LocalDate toLocalDate(Instant instant) {
		return instant.atOffset(ZoneOffset.UTC)
			.toLocalDate();
	}

	public static List<String> getPossiblePatterns() {
		return possiblePatterns;
	}
}
