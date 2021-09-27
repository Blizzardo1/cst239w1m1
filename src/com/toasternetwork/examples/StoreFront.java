package com.toasternetwork.examples;

import java.util.*;

public class StoreFront extends Inventory {
	private final List<Employee> employees;
	private final List<Register> registers;

	private final MainMenu mainMenu;
	private double storeFinance;

	private final long storeId;

	public StoreFront(long storeId, double storeFinance, MainMenu parent) {
		super(parent);
		this.storeId = storeId;
		this.storeFinance = storeFinance;
		employees = new ArrayList<>();
		registers = new ArrayList<>();
		mainMenu = parent;
	}

	/**
	 * Gets the income from the store
	 * @return The store's income
	 */
	public double getStoreIncome() {
		return storeFinance;
	}

	/**
	 * Gets the entire array of registers
	 * @return Returns a list of Registers.
	 */
	public List<Register> getRegisters() {
		return registers;
	}

	/**
	 * Gets a register by ID number
	 * @param id The ID of the register
	 * @return The register by ID
	 */
	public Register getRegister(int id) {
		if(id < 0) return null;

		System.out.printf("Using Register %d\n", id+1);
		return registers.get(id);
	}

	/**
	 * Adds a new Register with $100
	 */
	public void addRegister(int id) {
		double itill = 100;
		storeFinance -= itill;
		registers.add(new Register(itill, mainMenu, this));
		System.out.printf("Added Register %d with $%f\n", id, itill);
	}

	/**
	 * Bulk adds registers
	 * @param count How many registers to provision
	 */
	public void addRegisters(int count) {
		for(int i = 0; i < count; i++) {
			addRegister(i+1);
		}
	}

	/**
	 * Gets the Store's number
	 * @return The Store number
	 */
	public long getStoreId() {
		return storeId;
	}

	/**
	 * Gets the current Employees
	 * @return A list of employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	//TODO: Implement import from database upon store reset

	/**
	 * Adds a new Employee
	 * @param firstName Their first name
	 * @param lastName Their last namne
	 * @param employeeId Their new Id, must be unique
	 * @param wage Their starting salary
	 * @param position Their new position
	 * @param supervisor Supervisor approval
	 */
	public void hire(String firstName, String lastName, long employeeId, double wage, String position, Supervisor supervisor) {
		if(supervisor == null) return;

		// TODO: Implement supervisor approval

		Employee employee = new Employee(employeeId);
		employee.setName(firstName, lastName);
		employee.setWage(wage);
		employee.setPosition(position);
		employee.setSales(0);
		employees.add(employee);
	}

	/**
	 * Requires Management Approval — Terminates an Employee
	 * @param employee The Employee to fire
	 * @param reason The reason of their termination
	 * @param manager Signing Manager
	 * @return true if the Employee is successfully terminated
	 */
	public boolean terminate(Employee employee, String reason, Manager manager) {
		if(manager == null) return false;

		return employees.remove(employee);
	}
}
