package com.toasternetwork.examples;

public class Register extends ShoppingCart {
	private double sales;
	private double till;

	/**
	 * A new Register
	 * @param till The initial till
	 */
	public Register(double till) {
		super();
		this.till = till;
	}

	/**
	 * Opens the Drawer and dispenses the change
	 * @param payment The payment received
	 * @return The change
	 */
	public double receivePayment(double payment) {
		double change = Math.abs(calculateTotal() - payment);
		openDrawer();
		finalizeCart();
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
