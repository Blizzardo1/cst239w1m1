package com.toasternetwork.examples;

import java.util.*;

public class Inventory {
	private final List<Product> products;

	/**
	 * New Inventory
	 */
	public Inventory() {
		products = new ArrayList<>();
	}

	/**
	 * Adds a new product to inventory
	 * @param name The new name of the product
	 * @param description The description
	 * @param price The price
	 * @param quantity The quantity of the product
	 */
	public void addProduct(String name, String description, double price, long quantity) {
		Product p = new Product();
		p.setName(name);
		p.setDescription(description);
		p.setPrice(price);
		p.setQuantity(quantity);
		p.setSku(products.toArray().length + 1);
		products.add(p);
	}

	/**
	 * Gets the SKU of the product by name
	 * @param productName The product by name
	 * @return The product's SKU
	 */
	public long getProductSku(String productName) {
		Optional<Product> prod = products.stream().filter(p -> Objects.equals(p.getName(), productName)).findFirst();
		if(prod.isEmpty()) return 0;

		return prod.get().getSku();
	}

	/**
	 * Gets product by SKU
	 * @param sku The SKU
	 * @return The product
	 */
	public Product getProduct(long sku) {
		Optional<Product> prod = products.stream().filter(p -> Objects.equals(p.getSku(), sku)).findFirst();
		if(prod.isEmpty()) return null;

		return prod.get();
	}

	/**
	 * Updates the product's quantity
	 * @param sku The SKU
	 * @param quantity The new quantity
	 */
	public void updateProductQuantity(long sku, long quantity) {
		Product p = getProduct(sku);
		if(p == null) return;

		p.setQuantity(quantity);
	}

	/**
	 * Updates the product's price
	 * @param sku The SKU
	 * @param price The new price
	 */
	public void updateProductPrice(long sku, double price) {
		Product p = getProduct(sku);
		if(p == null) return;

		p.setPrice(price);
	}
}
