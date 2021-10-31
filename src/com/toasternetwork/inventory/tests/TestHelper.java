package com.toasternetwork.inventory.tests;

/**
 * Test Helper class for all Unit testing within this product
 */
public class TestHelper {

	/**
	 * Marks a test complete in the console
	 */
	public static void complete() {
		System.out.println(" ... Done");
	}

	/**
	 * Prints and makes a new line of the current test with an option to format the printed message
	 * @param src The currently performing test
	 * @param message The message to output
	 * @param args Optional arguments to format the message
	 */
	public static void println(String src, String message, Object ...args) {
		print(src, String.format("%s", message), args);
		System.out.println();
	}

	/**
	 * Prints the current test with an option to format the printed message
	 * @param src The currently performing test
	 * @param message The message to output
	 * @param args Optional arguments to format the message
	 */
	public static void print(String src, String message, Object ...args) {
		System.out.printf("%s [ %s ]", src, String.format(message, args));
	}
}
