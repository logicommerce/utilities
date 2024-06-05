package com.logicommerce.utilities.builders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.junit.jupiter.api.Test;

public class QueryParamsBuilderTest {

	@Test
	public void testBuildEmpty() {
		String result = new QueryParamsBuilder().toString();
		assertEquals(result, "");
	}

	@Test
	public void testBuildNullParams() {
		String result = new QueryParamsBuilder()
				.add(null, "value")
				.add("q", null)
				.toString();
		assertEquals(result, "");
	}

	@Test
	public void testRareParams() {
		String result = new QueryParamsBuilder()
				.add("q", new QueryParamsBuilder())
				.toString();
		assertNotEquals(result, "");
	}

	@Test
	public void testParams() throws UnsupportedEncodingException {
		String specialCharacters = "1234567890'¡ qwertyuiopasdfghjklñçzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM,"
			+ ".-ª!\"·$%&/()=?¿<>€\\|@#~½¬{[]}àèìòù"; 
		String result = new QueryParamsBuilder()
				.add("q", "theString")
				.add("page", 1)
				.add("sc", specialCharacters)
				.toString();
		String expected = "q=theString&page=1&sc=" + URLEncoder.encode(specialCharacters, "UTF-8");
		assertEquals(result, expected);
	}

	@Test
	public void testDuplicateParams() throws UnsupportedEncodingException {
		String result = new QueryParamsBuilder()
				.add("q", "theString")
				.add("page", 1)
				.add("page", 2)
				.add("page", 3)
				.toString();
		String expected = "q=theString&page=3";
		assertEquals(result, expected);
	}

}
