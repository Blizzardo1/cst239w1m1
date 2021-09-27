package com.toasternetwork.examples;

public class Helper {
	public static double round(double a, double decimal) {
		return Math.round(a * Math.pow(10, decimal)) / Math.pow(10, decimal);
	}

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
