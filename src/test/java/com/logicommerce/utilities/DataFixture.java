package com.logicommerce.utilities;

import java.time.LocalDateTime;

public class DataFixture {

	private String name;

	private String description;

	private int quantity;

	private double price;

	private LocalDateTime creationDate;

	public DataFixture() {
	}

	public DataFixture(final String name, String description, int quantity, double price, LocalDateTime creationDate) {
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.creationDate = creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

}
