package com.toasternetwork.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Product
 */
public class Product implements Cloneable, Comparable<Product> {
	private double price;
	private String name;
	private String description;
	private long quantity;
	private long sku;
	private long reserved;

	/**
	 * Gets the SKU for the product
	 * @return The SKU
	 */
	@JsonProperty("sku")
	public long getSku() {
		return sku;
	}

	/**
	 * Sets the new sku
	 * @param sku The replacing SKU number
	 */
	@JsonProperty("sku")
	public void setSku(long sku) {
		this.sku = sku;
	}

	/**
	 * Gets the amount of reserved quantity
	 * @return The total reserved amount
	 */
	public long getReserved() {
		return reserved;
	}

	/**
	 * Reserve stock from inventory
	 * @param quantity The amount of stock to reserve
	 */
	public void reserve(long quantity) {
		if(this.quantity < quantity) {
			System.out.println("There is not enough stock to reserve");
			return;
		}
		this.quantity -= quantity;
		reserved += quantity;
	}

	/**
	 * Release stock back into inventory
	 * @param quantity The amount of stock to replenish back to inventory
	 */
	public void release(long quantity) {
		if(quantity > reserved) {
			System.out.println("There is not enough reserved to release!");
			return;
		}
		this.quantity += quantity;
		reserved -= quantity;
	}

	/**
	 * Removes the amount of quantity from inventory
	 * @param quantity Quantity
	 */
	public void sell(long quantity) {
		this.quantity -= quantity;
	}

	/**
	 * Gets the current price for one quantity
	 * @return The current price
	 */
	@JsonProperty("price")
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the current price for one quanitty
	 * @param price The new Price
	 */
	@JsonProperty("price")
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the name of the product
	 * @return The current name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the product
	 * @param name The new name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the current description of the item
	 * @return The current description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the item
	 * @param description The new Description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the quanitity of the item in stock
	 * @return The current quantity
	 */
	@JsonProperty("quantity")
	public long getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity
	 * @param quantity The new quantity
	 */
	@JsonProperty("quantity")
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return String.format("%s\n\t%s\nSKU: %d\nPrice: %f\nStock: %d\nReserved: %d\n", name, description, sku, price, quantity, reserved);
	}

	@Override
	public int compareTo(Product p) {
		return name.compareToIgnoreCase(p.name);
	}
}
