package com.toasternetwork.inventory.tests;

import com.toasternetwork.inventory.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

	Inventory inv;
	String testName = "Inventory";

	@BeforeEach
	void setUp() {
		inv = new Inventory(new MainMenu());
		ProductDatabase pd = new ProductDatabase("test.json");
		Main.setInventory(pd);
		TestHelper.print(testName, "Checking if Inventory is in check");
		assertDoesNotThrow( () -> pd.readFile().forEach( p -> inv.addProduct(p)));
//		inv.getProducts().forEach( p -> {
//					TestHelper.println(testName, "Product: %s", p.toString());
//				});
		TestHelper.complete();
	}

	@AfterEach
	void tearDown() {
		inv = null;
	}

	@Test
	void addProduct() {
		TestHelper.print(testName, "Adding four products");
		assertAll( () -> {
			inv.addProduct("Test 1", "First Test Item", 1.0, 1);
			inv.addProduct("Test 2", "Second Test Item", 1.0, 5);
			inv.addProduct("Test 3", "Third Test Item", 1.0, 2);
			inv.addProduct("Test 4", "Fourth Test Item", 1.0, 4);
		});
		TestHelper.complete();
	}

	@Test
	void getProduct() {
		var p1 = inv.getProduct(1000);
		var p2 = inv.getProduct(1001);
		TestHelper.print(testName, "Asserting First Product is not null");
		assertNotNull( p1 );
		TestHelper.complete();
		TestHelper.print(testName, "Asserting Second Product is not null");
		assertNotNull( p2 );
		TestHelper.complete();
	}

	@Test
	void updateProductQuantity() {
		var p1 = inv.getProduct(1000);
		var p2 = inv.getProduct(1001);
		long p1Qty = 100;
		long p2Qty = 0;
		p1.setQuantity(p1Qty);
		p2.setQuantity(p2Qty);
		inv.updateProduct(p1);
		inv.updateProduct(p2);

		TestHelper.print(testName, "Checking first product's quantity");
		assertEquals(inv.getProduct(1000).getQuantity(), p1Qty);
		TestHelper.complete();
		TestHelper.print(testName, "Checking second product's quantity");
		assertEquals(inv.getProduct(1001).getQuantity(), p2Qty);
		TestHelper.complete();
	}

	@Test
	void updateProductPrice() {
		var p1 = inv.getProduct(1000);
		var p2 = inv.getProduct(1001);
		double p1Price = 395;
		double p2Price = 190;
		p1.setPrice(p1Price);
		p2.setPrice(p2Price);
		inv.updateProduct(p1);
		inv.updateProduct(p2);
		TestHelper.print(testName, "Checking first product's updated price");
		assertEquals(inv.getProduct(1000).getPrice(), p1Price, 0);
		TestHelper.complete();
		TestHelper.print(testName, "Checking second product's updated price");
		assertEquals(inv.getProduct(1001).getPrice(), p2Price, 0);
		TestHelper.complete();
	}

	@Test
	void updateProduct() {
		var p1 = inv.getProduct(1000);
		var p2 = inv.getProduct(1001);
		String p1Name = "CZ75 P-01";
		String p2Name = "Master Sword";
		p1.setName(p1Name);
		p2.setName(p2Name);
		inv.updateProduct(p1);
		inv.updateProduct(p2);
		TestHelper.print(testName, "Checking first product's updated name");
		assertEquals(inv.getProduct(1000).getName(), p1Name);
		TestHelper.complete();
		TestHelper.print(testName, "Checking second product's updated name");
		assertEquals(inv.getProduct(1001).getName(), p2Name);
		TestHelper.complete();
	}
}