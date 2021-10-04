package com.toasternetwork.examples;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A Register; Handles order processing
 */
public class Register extends ShoppingCart {
	private double sales;
	private double till;
	private int registerId;
	private Inventory inventory;

	private static AtomicInteger regId = new AtomicInteger(1);

	/**
	 * A new Register
	 * @param till The initial till
	 * @param mainMenu The Parent Menu
	 * @param inventory The Inventory
	 */
	public Register(double till, MainMenu mainMenu, Inventory inventory) {
		super(mainMenu);
		this.till = till;
		this.inventory = inventory;
		registerId = regId.getAndIncrement();
		menu.put("Add Product", m -> {
			Helper.clearScreen();
			System.out.print("SKU: ");
			long sku = input.nextLong();
			System.out.print("Quantity: ");
			long qty = input.nextLong();
			Product p = inventory.getProduct(sku);
			if(p == null) {
				System.err.printf("SKU: %d does not exist!\n", sku);
				return this;
			}

			addProduct(p, qty);
			return this;

		});
		menu.put("Process Payment", m -> {
			Helper.clearScreen();
			double total = calculateTotal(true);
			System.out.print("Amount Tendered:");
			double tendered = input.nextDouble();
			if(tendered < total) {
				System.err.println("Not enough tendered!");
				return this;
			}
			double change = receivePayment(tendered);
			System.out.printf("Your Change is %f\n", change);
			for(Product p : cart){
				long qty = inventory.getProduct(p.getSku()).getQuantity();
				inventory.getProduct(p.getSku()).release(p.getQuantity());
				inventory.updateProductQuantity(p.getSku(), qty);
			}
			finalizeCart();
			return this;
		});
		menu.put("Cancel order", m -> {
			cancelOrder();
			return m;
		});
		menu.put("Show Till", m -> {
			Helper.clearScreen();
			System.out.printf("Current Till for Register %d: %f\n", registerId, this.till);
			return this;
		});
	}

	/**
	 * Gets the register ID number
	 * @return The Register's Identification number
	 */
	public int getId() {
		return registerId;
	}

	/**
	 * Opens the Drawer and dispenses the change
	 * @param payment The payment received
	 * @return The change
	 */
	public double receivePayment(double payment) {
		double total = calculateTotal(false);
		double change = Math.abs( total - payment);
		till += total - change;
		openDrawer();
		return change;
	}

	/**
	 * Opens the till drawer
	 * @return true if the drawer successfully opens
	 */
	public boolean openDrawer() {
		// TODO: Implement hardware calls
		return true;
	}
}
