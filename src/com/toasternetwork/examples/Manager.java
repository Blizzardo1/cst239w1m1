package com.toasternetwork.examples;

public class Manager extends Supervisor {

	public Manager(long employeeId) {
		super(employeeId);
	}

	public boolean terminate(StoreFront store, Employee employee, String reason) {
		return store.terminate(employee, reason, this);
	}
}
