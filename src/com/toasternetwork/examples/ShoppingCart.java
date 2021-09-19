package com.toasternetwork.examples;

import java.util.*;

public class ShoppingCart {
	private final double tax = 1.0775;
	private final List<Product> cart;

	/**
	 * A New Shopping Cart
	 */
	public ShoppingCart() {
		cart = new ArrayList<>();
	}

	/**
	 * Adds a product to the cart
	 * @param product The product
	 */
	public void addProduct(Product product, long quantity) {
		product.setQuantity(quantity);
		cart.add(product);
	}

	/**
	 * Updates the quantity of the current SKU product
	 * @param sku The SKU to look for
	 * @param quantity The new quantity
	 */
	public void updateProductQuantity(long sku, long quantity) {
		// WARNING: Cheap trick to recall a product if it exists in the list, updates it, then re-flashes to the list
		Product product = getProduct(sku);

		if(product == null) return;

		removeProduct(product);

		product.setQuantity(quantity);
		cart.add(product);
	}

	/**
	 * Gets the product by SKU number
	 * @param sku The SKU to find
	 * @return The product from the SKU
	 */
	public Product getProduct(long sku) {
		var pa = cart.stream().filter(p -> p.getSku() == sku).findFirst();
		Product product = null;
		if(pa.isPresent())
			product = pa.get();

		return product;
	}

	/**
	 * Takes final steps in transaction process
	 */
	public void finalizeCart() {
		// I know what you're thinking, this is the same method as cancelOrder(), well both methods
		// don't have enough functions yet to differentiate, so yes, they have the same call for now.
		System.out.println("Your receipt");
		cart.clear();
	}

	/**
	 * Removes an item from the cart if the cart has the product
	 * @param product The product to remove
	 * @return true if the product was successfully removed from the cart
	 */
	public boolean removeProduct(Product product) {
		if(product == null || !cart.contains(product)) return false;

		return cart.remove(product);
	}

	public double calculateTotal() {
		if(cart.size() == 0) {
			System.out.println("Cart is empty!");
			return 0;
		}

		double subtotal = cart.stream().mapToDouble(r -> r.getPrice() * r.getQuantity()).sum();
		double total = MathHelper.round(subtotal * tax,2);

		System.out.println("-----------------------------------------------");
		System.out.println("Qty\tItem\t\t\tPrice");
		System.out.println("---\t----\t\t\t-----");
		cart.forEach(p -> System.out.printf("%d\t%s\t\t\t$%.2f\n", p.getQuantity(), p.getName(), p.getPrice()));
		System.out.printf("Subtotal: $%.2f\n", subtotal);
		System.out.printf("     Tax: %.2f%%\n", (tax - 1) * 100);
		System.out.printf("   Total: $%.2f\n", total);
		System.out.println("================================================");
		return total;
	}

	/**
	 * Clears out the entire order
	 */
	public void cancelOrder() {
		System.out.println("Order cancelled");
		cart.clear();
	}
}
