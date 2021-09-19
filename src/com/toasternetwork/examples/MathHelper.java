package com.toasternetwork.examples;

public class MathHelper {
	public static double round(double a, double decimal) {
		return Math.round(a * Math.pow(10, decimal)) / Math.pow(10, decimal);
	}
}
