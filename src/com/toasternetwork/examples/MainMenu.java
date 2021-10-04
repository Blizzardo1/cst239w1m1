package com.toasternetwork.examples;

public class MainMenu extends Menulet<Object> {
	private StoreFront store;
	private RegisterMenu registers;

	public MainMenu() {
		super();
		build();
	}

	public RegisterMenu getRegisters() {
		return registers;
	}

	public void setRegisters(RegisterMenu registers) {
		this.registers = registers;
	}

	public StoreFront getStore() {
		return store;
	}

	public void setStore(StoreFront store) {
		this.store = store;
	}

	@Override
	public void build() {
		if(isBuilt) return;

		menu.put("Registers", m -> getRegisters());
		menu.put("Inventory", m -> store);
		menu.put("Management", m -> null);
		menu.put("Close Store", m -> {
			Helper.clearScreen();
			m.display();
			System.out.println(m);
			System.out.println("Goodbye!");
			System.exit(0);
			return null;
		});
		isBuilt = true;
	}
}
