package com.toasternetwork.inventory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An Employee
 */
public class Employee implements Comparable<Employee> {
	private String firstName;
	private String lastName;
	private long employeeId;
	private double wage;
	private String position;
	private Supervisor supervisor;
	private Role role;
	private double sales;
	private String password;

	/**
	 * A Blank Employee
	 */
	@JsonCreator
	public Employee() {

	}

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
	@JsonProperty("supervisor")
	public Supervisor getSupervisor() {
		return supervisor;
	}

	/**
	 * Sets the supervisor on duty
	 * @param supervisor The new supervisor on duty
	 */
	@JsonProperty("supervisor")
	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	/**
	 * Gets the employee password
	 * @return The password hash for the Employee
	 */
	@JsonProperty("password")
	public String getSecurePassword() {
		return password;
	}

	/**
	 * Sets the password for the employee
	 * @param password The new password
	 */
	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the user role for the current Employee
	 * @return The role assigned by management
	 */
	@JsonProperty("role")
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the user role for the current Employee
	 * @param role The role assigned by management
	 */
	@JsonProperty("role")
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Gets the Employee's first and last name
	 * @return Employee's name
	 */
	@JsonProperty("name")
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
	 * Sets the Employee's first and last name
	 * @param name Their name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		String[] aName = name.split(" ");
		this.firstName = aName[0];
		this.lastName = aName[1];
	}

	/**
	 * Gets the Employee's ID
	 * @return Employee ID
	 */
	@JsonProperty("employeeId")
	public long getEmployeeId() {
		return employeeId;
	}

	/**
	 * Sets the Employee ID
	 * @param employeeId The new Employee ID
	 */
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * Gets the Employee's Wage
	 * @return Wage in USD
	 */
	@JsonProperty("wage")
	public double getWage() {
		return wage;
	}

	/**
	 * Sets the wage of the Employee
	 * @param wage The new Wage
	 */
	@JsonProperty("wage")
	public void setWage(double wage) {
		this.wage = wage;
	}

	/**
	 * Gets the current position of the Employee
	 * @return The current position within the store
	 */
	@JsonProperty("position")
	public String getPosition() {
		return position;
	}

	/**
	 * Sets the position of the Employee
	 * @param position The new position of power and responsibilities
	 */
	@JsonProperty("position")
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Gets the sales for the Employee
	 * @return The Employee's current sales
	 */
	@JsonProperty("sales")
	public double getSales() {
		return sales;
	}

	/**
	 * Sets the sales of the Employee
	 * @param sales New sales total
	 */
	@JsonProperty("sales")
	public void setSales(double sales) {
		this.sales = sales;
	}


	@Override
	public String toString() {
		return "Employee{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", employeeId=" + employeeId +
				", wage=" + wage +
				", position='" + position + '\'' +
				", supervisor=" + supervisor +
				", role=" + role +
				", sales=" + sales +
				'}';
	}

	@Override
	public int compareTo(Employee o) {
		return this.employeeId == o.employeeId ? 1 : 0;
	}
}
