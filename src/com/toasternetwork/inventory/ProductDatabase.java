package com.toasternetwork.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * An extended instance of the Database for Products
 */
public class ProductDatabase extends Database<Product> {
	/**
	 * A new Product Database
	 * @param fileName The file to load the database from
	 */
	public ProductDatabase(String fileName) {
		super(fileName);
	}

	@Override
	public List<Product> readFile() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return List.of(mapper.readValue(Paths.get(getFileName()).toFile(), Product[].class));
	}
}
