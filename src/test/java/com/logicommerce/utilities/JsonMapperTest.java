package com.logicommerce.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class JsonMapperTest {

	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final int QUANTITY = 1;
	private static final double PRICE = 1.23;
	private static final LocalDateTime CREATION_DATE = LocalDateTime.now().minusMinutes(10);
	private static final String YESTERDAY = "2021-01-25T12:34:56.789123456Z";

	@Test
	public void testToJson() throws JsonConverterException {
		final JsonMapper<DataFixture> mapper = new JsonMapper<>(DataFixture.class);
		DataFixture data = new DataFixture(NAME, DESCRIPTION, QUANTITY, PRICE, CREATION_DATE);
		String json = mapper.toJson(data);
		assertNotNull(json);
		DataFixture result = mapper.fromJson(json);
		assertNotNull(result);
		assertEquals(data.getName(), NAME);
		assertEquals(data.getDescription(), DESCRIPTION);
		assertEquals(data.getQuantity(), QUANTITY);
		assertEquals(data.getPrice(), PRICE);
		assertEquals(data.getCreationDate(), CREATION_DATE);
	}

	@Test
	public void testFromJson() throws JsonConverterException {
		final JsonMapper<DataFixture> mapper = new JsonMapper<>(DataFixture.class);
		String json = "{\"name\":\"" + NAME
			+ "\",\"description\":\"" + DESCRIPTION
			+ "\",\"quantity\": " + QUANTITY
			+ ",\"price\":" + PRICE
			+ ",\"creationDate\":\"" + YESTERDAY + "\"}";
		DataFixture data = mapper.fromJson(json);
		assertNotNull(data);
		assertEquals(data.getName(), NAME);
		assertEquals(data.getDescription(), DESCRIPTION);
		assertEquals(data.getQuantity(), QUANTITY);
		assertEquals(data.getPrice(), PRICE);
		assertNotNull(data.getCreationDate());
	}

}
