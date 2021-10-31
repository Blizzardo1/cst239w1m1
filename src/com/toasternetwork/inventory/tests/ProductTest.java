package com.toasternetwork.inventory.tests;


import com.toasternetwork.inventory.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
	Inventory i;
	String testName;


	@BeforeEach
	void setUp() {
		i = new Inventory(new MainMenu());
		ProductDatabase pd = new ProductDatabase("test.json");
		Main.setInventory(pd);
		assertDoesNotThrow(() -> pd.readFile().forEach(p -> i.addProduct(p)));
	}

	@AfterEach
	void tearDown() {
		i = null;
	}

	@Test
	void reserve() {
		testName = "Reserve";
		Random r = new Random();
		Product p = i.getProduct(1);
		long qty = r.nextLong(p.getQuantity() - 1);
		p.reserve(qty);
		TestHelper.println(testName, "Expected: %d, Actual: %d", qty, p.getReserved());
		assertEquals(p.getReserved(), qty);
	}

	@Test
	void release() {
		testName = "Release";
		Random r = new Random();
		Product p = i.getProduct(1);
		long qty = r.nextLong(p.getQuantity() - 1);
		long release = r.nextLong(qty / 2);
		long expected;
		p.reserve(qty);
		TestHelper.println(testName, "Expected: %d, Actual: %d", qty, p.getReserved());
		assertEquals(p.getReserved(), qty);
		p.release(release);
		expected = qty - (qty - release); // Should equate to the amount released
		TestHelper.println(testName, "Expected: %d, Actual: %d ", expected, release);
		assertEquals(expected, release);
		TestHelper.println(testName, "(Reserve Count) Expected: %d, Actual: %d", qty - release, p.getReserved());
	}
}