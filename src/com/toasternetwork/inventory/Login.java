package com.toasternetwork.inventory;

import java.io.Console;
import java.io.IOException;
import java.util.*;

/**
 * Login Menu
 */
public class Login extends Menulet<Object> {

	/**
	 * A basic instance of the Login Menu
	 */
	public Login() {
		super();
		build();
	}

	/**
	 * Attempts to authenticate an employee
	 * @return An authenticated employee
	 */
	public Employee login() {
		Scanner in = new Scanner(System.in);

		System.out.print("Employee Id: ");
		long user = in.nextLong();
		in.nextLine();
		System.out.print("Password: ");
		String pass = in.nextLine();

		try {
			List<Employee> employees = Main.getEmployees().readFile();
			Optional<Employee> emp = employees.stream().filter(e -> e.getEmployeeId() == user).findFirst();
			if(emp.isPresent()) {
				Employee employee = emp.get();

				//System.out.printf("%s == %s?\n", employee.getSecurePassword(), pass);
				if(!Objects.equals(employee.getSecurePassword(), pass)) {
					System.out.println("Id or Password incorrect");
					return null;
				}
				return emp.get();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Builds the menu
	 */
	@Override
	void build() {
		if(isBuilt) return;

		menu.put("Login", m -> {
			var emp = login();
			if(emp == null) return m;
			Main.setLoggedIn(emp);
			return Main.getMainMenu();
		});
		menu.put("Close", m -> {
			Helper.clearScreen();
			System.out.println("Goodbye!");
			System.exit(0);
			return null;
		});
	}
}
