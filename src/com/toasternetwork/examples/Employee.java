package com.toasternetwork.examples;

/**
 * An Employee
 */
public class Employee {
	private String firstName;
	private String lastName;
	private final long employeeId;
	private double wage;
	private String position;
	private Supervisor supervisor;


	/**
	 * A new Employee
	 * @param employeeId The Employee's Identification Number
	 */
	public Employee(long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * Gets the Employee's supervisor
	 * @return The supervisor on duty
	 */
	public Supervisor getSupervisor() {
		return supervisor;
	}

	/**
	 * Sets the supervisor on duty
	 * @param supervisor The new supervisor on duty
	 */
	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	/**
	 * Gets the Employee's first and last name
	 * @return Employee's name
	 */
	public String getName() {
		return String.format("%s %s", firstName, lastName);
	}

	/**
	 * Sets the Employee's first and last name
	 * @param firstName Their first name
	 * @param lastName Their last name
	 */
	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Gets the Employee's ID
	 * @return Employee ID
	 */
	public long getEmployeeId() {
		return employeeId;
	}

	/**
	 * Gets the Employee's Wage
	 * @return Wage in USD
	 */
	public double getWage() {
		return wage;
	}

	/**
	 * Sets the wage of the Employee
	 * @param wage The new Wage
	 */
	public void setWage(double wage) {
		this.wage = wage;
	}

	/**
	 * Gets the current position of the Employee
	 * @return The current position within the store
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Sets the position of the Employee
	 * @param position The new position of power and responsibilities
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Gets the sales for the Employee
	 * @return The Employee's current sales
	 */
	public double getSales() {
		return sales;
	}

	/**
	 * Sets the sales of the Employee
	 * @param sales New sales total
	 */
	public void setSales(double sales) {
		this.sales = sales;
	}

	private double sales;

}
