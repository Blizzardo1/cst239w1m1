package com.toasternetwork.inventory.tests;

import com.toasternetwork.inventory.MainMenu;
import com.toasternetwork.inventory.StoreFront;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreFrontTest {

	StoreFront sf;
	String testName = "Store Front";

	@BeforeEach
	void setUp() {
		sf = new StoreFront(1,  1000, new MainMenu());
	}

	@AfterEach
	void tearDown() {
		sf = null;
	}

	@Test
	void getStoreIncome() {
		TestHelper.print(testName, "Checking store income is greater than zero");
		assertTrue(sf.getStoreIncome() > 0);
		TestHelper.complete();
	}

	@Test
	void addRegister() {
		TestHelper.print(testName, "Adding a register");
		assertAll(() -> sf.addRegister(1));
		TestHelper.complete();

	}

	@Test
	void getStoreId() {
		TestHelper.print(testName, "Checking store ID is not zero");
		assertTrue(sf.getStoreId() != 0);
		TestHelper.complete();
	}
}