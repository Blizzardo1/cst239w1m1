package com.toasternetwork.inventory;

/**
 * A Supervisor
 */
public class Supervisor extends Employee {

	/**
	 * A Supervisor
	 * @param employeeId Supervisor's employee ID
	 */
	public Supervisor(long employeeId) {
		super(employeeId);
	}

	/**
	 * Adds a new Employee
	 * @param store The store
	 * @param firstName Their first name
	 * @param lastName Their last name
	 * @param employeeId Their new Id, must be unique
	 * @param wage Their starting salary
	 * @param position Their new position
	 * @param role The role assigned
	 */
	public void hire(StoreFront store, String firstName, String lastName, long employeeId, double wage, String position, Role role) {
		store.hire(firstName, lastName, employeeId, wage, position, role, this);
	}

	/**
	 * RESERVED - Submits a report of tills of each register, the inventory, and employee reports.
	 */
	public void submitReport() {

	}
}
