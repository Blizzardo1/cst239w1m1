package com.toasternetwork.inventory;

/**
 * A Manager
 */
public class Manager extends Supervisor {

	private static final Manager root;

	static {
		root = new Manager(0);
		root.setName("Root", "Administrator");
		root.setRole(Role.Administrator);
		root.setPosition("Root");
	}

	/**
	 * Gets the ultimate root user of this program
	 * @return The root user that NO ONE SHOULD HAVE ACCESS TO!
	 */
	public static Manager getRoot() {
		return root;
	}

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
