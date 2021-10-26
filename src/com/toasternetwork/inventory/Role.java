package com.toasternetwork.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Roles!
 */
public enum Role {
	/**
	 * The Administrator
	 */
	@JsonProperty("Administrator")
	Administrator,
	/**
	 * A Simple User
	 */
	@JsonProperty("User")
	User
}
