package com.toasternetwork.examples;

public class Supervisor extends Employee {

	public Supervisor(long employeeId) {
		super(employeeId);
	}

	/**
	 * Adds a new Employee
	 * @param firstName Their first name
	 * @param lastName Their last namne
	 * @param employeeId Their new Id, must be unique
	 * @param wage Their starting salary
	 * @param position Their new position
	 */
	public void hire(StoreFront store, String firstName, String lastName, long employeeId, double wage, String position) {
		store.hire(firstName, lastName, employeeId, wage, position, this);
	}

	public void submitReport() {

	}
}
