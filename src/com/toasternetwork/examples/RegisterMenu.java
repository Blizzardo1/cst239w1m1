package com.toasternetwork.examples;

import java.util.List;

public class RegisterMenu extends Menulet<Object> {

	private final MainMenu parent;
	private final List<Register> registers;

	private Register currentRegister;

	public RegisterMenu(List<Register> registers, MainMenu parent) {
		this.registers = registers;
		this.parent = parent;
		build();
	}

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
