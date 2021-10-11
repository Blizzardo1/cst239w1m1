package com.toasternetwork.examples;

import java.io.IOException;

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
		menu.put("Management", m -> null);
		menu.put("Commit Inventory", m -> {
			try {
				InventoryDatabase.writeContents(store.getProducts());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return m;
		});
		menu.put("Close Store", m -> {
			Helper.clearScreen();
			System.out.println(m);
			System.out.println("Goodbye!");
			System.exit(0);
			return null;
		});
		isBuilt = true;
	}
}
