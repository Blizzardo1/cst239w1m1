package com.toasternetwork.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * An extended version of Database
 */
public class EmployeeDatabase extends Database<Employee> {
	/**
	 * A new instance of the Employee Database
	 * @param fileName The filename of the database to load
	 */
	public EmployeeDatabase(String fileName) {
		super(fileName);
	}

	@Override
	public List<Employee> readFile() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return List.of(mapper.readValue(Paths.get(getFileName()).toFile(), Employee[].class));
	}
}
