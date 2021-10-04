package com.toasternetwork.examples;

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
	 */
	public void hire(StoreFront store, String firstName, String lastName, long employeeId, double wage, String position) {
		store.hire(firstName, lastName, employeeId, wage, position, this);
	}

	/**
	 * RESERVED - Submits a report of tills of each register, the inventory, and employee reports.
	 */
	public void submitReport() {

	}
}
