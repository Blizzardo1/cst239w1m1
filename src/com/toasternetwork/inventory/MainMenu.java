package com.toasternetwork.inventory;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * The Main Menu
 */
public class MainMenu extends Menulet<Object> {
	private StoreFront store;
	private RegisterMenu registers;

	/**
	 * A new Main Menu
	 */
	public MainMenu() {
		super();
		build();
	}

	private void rebuild() {
		isBuilt = false;
		menu.clear();
		build();
	}

	/**
	 * Gets the currently linked RegisterMenu
	 * @return The Register Menu
	 */
	public RegisterMenu getRegisters() {
		return registers;
	}

	/**
	 * Links the Register Menu within the menu
	 * @param registers The Register Menu
	 */
	public void setRegisters(RegisterMenu registers) {
		this.registers = registers;
	}

	/**
	 * Gets the currently selected store
	 * @return An instance of a Store
	 */
	public StoreFront getStore() {
		return store;
	}

	/**
	 * Links the store within the menu
	 * @param store Links the store to the main menu
	 */
	public void setStore(StoreFront store) {
		this.store = store;
	}

	@Override
	public void build() {
		if(isBuilt) return;

		menu.put("Registers", m -> getRegisters());
		menu.put("Inventory", m -> store);
		menu.put("Management", m -> {
			if (!checkPermission())
				return this;
			var lst = store.getEmployees();
			if(lst.isEmpty()) {
				System.out.println("No Employees registered!");
				return m;
			}
			Scanner in = new Scanner(System.in);
			System.out.print("Employee ID: ");
			long id = in.nextLong();

			Employee employee = null;
			var optionalEmployee = lst.stream().filter(e -> e.getEmployeeId() == id).findFirst();
			if(optionalEmployee.isPresent())
				employee = optionalEmployee.get();

			assert employee != null;
			System.out.println(employee);

			return m;
		});
		menu.put("Commit Inventory", m -> {
			try {
				Main.getInventory().writeContents(store.getProducts());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return m;
		});
		menu.put("Logout", m -> {
			Main.setLoggedIn(null);
			return Main.getLogin();
		});
		menu.put("Super User", m -> {
			Scanner in = new Scanner(System.in);
			System.out.print("Password: ");
			String password = in.nextLine();
			Employee root = store.getEmployees().get(0);
			if(root.getEmployeeId() == 0
					&& Objects.equals(root.getSecurePassword(), password)) {
				System.out.println("Root User");
				Main.setLoggedIn(root);
			}
			rebuild();
			return this;
		});
		menu.put("Get Logged In", m ->{
			Stack<Employee> emps = Main.getLoggedInEmployees();
			for(Employee e : emps) {
				System.out.printf("%d: %s\n", e.getEmployeeId(), e.getName());
			}
			System.out.printf("%d: %s\n", Main.getLoggedIn().getEmployeeId(), Main.getLoggedIn().getName());
			return this;
		});
		if(Main.getLoggedIn() != null && Main.getLoggedIn().getEmployeeId() == 0) {
			menu.put("Logout Root", m -> {
				Main.logoutEmployee();
				rebuild();
				return this;
			});
		}
		menu.put("Close Store", m -> {
			if (!checkPermission())
				return this;
			Helper.clearScreen();
			System.out.println("Goodbye!");
			System.exit(0);
			return null;
		});
		isBuilt = true;
	}
}
