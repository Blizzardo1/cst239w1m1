package com.toasternetwork.inventory.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toasternetwork.inventory.Database;
import com.toasternetwork.inventory.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
	@Test
	void open() {
		Database<Product> dataObj = new Database<>("test.json");
		try {
			assertDoesNotThrow(dataObj::open, "Uh Oh!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void readFile() {
		Database<Product> dataObj = new Database<>("test.json");
		TestHelper.print("Database", "Reading file");
		assertDoesNotThrow(dataObj::readFile, "Failed to parse file");
		System.out.println(" ... Done");
	}

	@Test
	void writeContents() {
		Database<Product> dataObj = new Database<>("test.json");
		String json = """
				[ {
				  "price" : 400.0,
				  "name" : "9mm Pistol",
				  "description" : "A 9mm Pistol that is not a HighPoint",
				  "quantity" : 150,
				  "sku" : 1
				}, {
				  "price" : 200.0,
				  "name" : "Sword",
				  "description" : "Link's sword from Twilight Princess",
				  "quantity" : 1,
				  "sku" : 2
				}, {
				  "price" : 50.0,
				  "name" : "Light Armor",
				  "description" : "Light Armor",
				  "quantity" : 1000,
				  "sku" : 3
				}, {
				  "price" : 85.0,
				  "name" : "Heavy Armor",
				  "description" : "Super duper heavy armor",
				  "quantity" : 1000,
				  "sku" : 4
				}, {
				  "price" : 3.75,
				  "name" : "10 HP",
				  "description" : "Ten Health Points",
				  "quantity" : 998300,
				  "sku" : 5
				}, {
				  "price" : 1000.0,
				  "name" : "Full Health",
				  "description" : "100% Health restoration",
				  "quantity" : 1000,
				  "sku" : 6
				} ]
				""";
		ObjectMapper om = new ObjectMapper();
		ArrayList<Product> lst = new ArrayList<>();
		TestHelper.print("Database", "Adding all products from file");
		assertDoesNotThrow(() -> Collections.addAll(lst, om.readValue(json, Product[].class)));
		System.out.println(" ... Done");
		TestHelper.print("Database", "Writing contents to file");
		assertDoesNotThrow(() -> dataObj.writeContents(lst));
		System.out.println(" ... Done");
	}
}