package com.toasternetwork.examples;

/**
 * A Manager
 */
public class Manager extends Supervisor {

	/**
	 * A new manager
	 * @param employeeId The employee ID of the Manager
	 */
	public Manager(long employeeId) {
		super(employeeId);
	}

	/**
	 * Terminates an employee under the supervision of the Manager
	 * @param store The Store of which to fire the employee
	 * @param employee The employee to be fired
	 * @param reason The reason for termination
	 * @return True if the termination was a success
	 */
	public boolean terminate(StoreFront store, Employee employee, String reason) {
		return store.terminate(employee, reason, this);
	}
}
