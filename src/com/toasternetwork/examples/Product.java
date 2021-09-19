package com.toasternetwork.examples;

public class Product {
	private double price;
	private String name;
	private String description;
	private long quantity;
	private long sku;

	/**
	 * Gets the SKU for the product
	 * @return The SKU
	 */
	public long getSku() {
		return sku;
	}

	/**
	 * Sets the new sku
	 * @param sku The replacing SKU number
	 */
	public void setSku(long sku) {
		this.sku = sku;
	}

	/**
	 * Gets the current price for one quantity
	 * @return The current price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the current price for one quanitty
	 * @param price The new Price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the name of the product
	 * @return The current name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the product
	 * @param name The new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the current description of the item
	 * @return The current description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the item
	 * @param description The new Description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the quanitity of the item in stock
	 * @return The current quantity
	 */
	public long getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity
	 * @param quantity The new quantity
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
}
