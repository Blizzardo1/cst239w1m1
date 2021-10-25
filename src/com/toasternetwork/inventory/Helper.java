package com.toasternetwork.inventory;

/**
 * Helper Class
 */
public class Helper {
	/**
	 * Rounds a number to the specified decimal
	 * @param a The 64-bit floating point
	 * @param decimal the number of decimals to round to
	 * @return A perfectly rounded number
	 */
	public static double round(double a, double decimal) {
		return Math.round(a * Math.pow(10, decimal)) / Math.pow(10, decimal);
	}

	/**
	 * Clears the screen! *Does not work in integrated terminals such as IntelliJ
	 */
	public static void clearScreen() {
		try
		{
				System.out.print("\033[H\033[2J");
				System.out.flush();
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}
}
