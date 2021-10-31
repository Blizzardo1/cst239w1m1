package com.toasternetwork.inventory.tests;

import com.toasternetwork.inventory.MainMenu;
import com.toasternetwork.inventory.Product;
import com.toasternetwork.inventory.ShoppingCart;
import com.toasternetwork.inventory.StoreFront;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

	ShoppingCart cart;
	String testName = "Shopping Cart";
	Product testProduct;
	Product testRemove;
	Random rand;

	@BeforeEach
	void setUp() {
		MainMenu mm = new MainMenu();
		StoreFront sf = new StoreFront(100, 10000, mm);
		mm.setStore(sf);
		cart = new ShoppingCart(mm);
		rand = new Random();
		testProduct = new Product();
		testProduct.setName("Test");
		testProduct.setDescription("Some Description");
		testProduct.setSku(1000);
		testProduct.setPrice(32);
		testProduct.setQuantity(100);
		testRemove = new Product();
		testRemove.setName("DeleteMe");
		testRemove.setDescription("No seriously, Delete me!");
		testRemove.setPrice(9999);
		testRemove.setQuantity(50);
		testRemove.setSku(1001);
		sf.addProduct(testRemove);
		sf.addProduct(testProduct);
		cart.addProduct(testRemove, rand.nextLong(testRemove.getQuantity() - 1));
	}

	@AfterEach
	void tearDown() {
		cart = null;
	}

	@Test
	void addProduct() {
		long qty = rand.nextLong(testProduct.getQuantity() - 1);
		TestHelper.print(testName, "Checking quantity reservation");
		assertTrue(qty < testProduct.getQuantity());
		cart.addProduct(testProduct, qty);
		assertEquals(qty, testProduct.getReserved());
		TestHelper.complete();
		TestHelper.println(testName, "Expected: %d, Actual: %d", qty, testProduct.getReserved());

		System.out.println("Product added successfully");
	}

	@Test
	void updateProductQuantity() {
		long newQuantity = rand.nextLong(testProduct.getQuantity() + 100);
		testProduct.setQuantity(newQuantity);
		TestHelper.print(testName, "Checking updated quantity");
		assertEquals(newQuantity, testProduct.getQuantity());
		TestHelper.complete();
		TestHelper.println(testName, "Expected: %d, Actual: %d", newQuantity, testProduct.getQuantity());
	}

	@Test
	void finalizeCart() {

		cart.finalizeCart();
		TestHelper.print(testName, "Checking if cart is empty");
		assertEquals(0, cart.getCartCount());
		TestHelper.complete();
	}

	@Test
	void removeProduct() {
		cart.removeProduct(testRemove);
		TestHelper.print(testName, "Checking if DeleteMe Product is still in cart");
		assertFalse(cart.containsProduct(testRemove));
		TestHelper.complete();
	}

	@Test
	void calculateTotal() {
		double total = cart.calculateTotal(false);
		TestHelper.print(testName, "Checking if cart has a balance");
		assertTrue(total > 0);
		TestHelper.complete();
	}

	@Test
	void cancelOrder() {
		cart.cancelOrder();
		TestHelper.print(testName, "Checking if cart is cancelled");
		assertEquals(0, cart.getCartCount());
		TestHelper.complete();
	}
}