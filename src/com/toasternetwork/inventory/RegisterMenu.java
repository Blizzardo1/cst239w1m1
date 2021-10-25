package com.toasternetwork.inventory;

import java.util.List;

/**
 * A menu of registers
 */
public class RegisterMenu extends Menulet<Object> {

	private final MainMenu parent;
	private final List<Register> registers;

	private Register currentRegister;

	/**
	 * A new Register Menu
	 * @param registers The list of Registers
	 * @param parent The Parent Menu
	 */
	public RegisterMenu(List<Register> registers, MainMenu parent) {
		this.registers = registers;
		this.parent = parent;
		build();
	}

	/**
	 * Gets the currently selected Register
	 * @return An instance of a Register
	 */
	public Register getCurrentRegister() {
		return currentRegister;
	}

	@Override
	void build() {
		for(Register r : registers) {
			super.menu.put(String.format("Register %d", r.getId()), (m) -> {
				currentRegister = r;
				System.out.printf("Register %d has been selected\n", r.getId());
				return currentRegister;
			});
		}
		super.menu.put("Main Menu", m -> parent);
	}
}
