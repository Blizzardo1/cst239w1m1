package com.toasternetwork.inventory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * A Menulet
 * @param <T> A generic specification of any returnable type
 */
public abstract class Menulet<T> {
	/**
	 * The core menu mapping with a function binding of a wildcard menulet and a returnable generic specified returnable type
	 */
	protected LinkedHashMap<String, Function<Menulet<?>,T>> menu;

	/**
	 * Whether the menu is built or not. *Prevents rebuilding of the menu unless the menu needs to be rebuilt*
	 */
	protected boolean isBuilt;

	/**
	 * A new instance of a Menulet
	 */
	public Menulet() {
		menu = new LinkedHashMap<>();
	}

	/**
	 * Displays the list of items within a menu
	 */
	void display(){
		AtomicInteger i = new AtomicInteger(1);
		menu.forEach((c,m) -> System.out.printf("%d) %s\n", i.getAndIncrement(),c));
	}

	/**
	 * Choose a menu item by number
	 * @return The number selected within the bounds of the menu
	 */
	public int choose() {
		Scanner sc = new Scanner(System.in);

		display();

		System.out.print("Option: ");
		int result = sc.nextInt();
		if(result < 1 || result > menu.size())
			return 0;
		return result-1;
	}

	/**
	 * Executes a Menu Action
	 * @param option A numerical option to pass through
	 * @return A generic specification of any returnable type.
	 */
	public T execute(int option) {
		String key = (String)menu.keySet().toArray()[option];
		return menu.get(key).apply(this);
	}

	/**
	 * Checks whether the logged in employee has access to a menu function
	 * @return True if allowed
	 */
	public boolean checkPermission() {
		if(Main.getLoggedIn().getRole() != Role.Administrator) {
			System.out.println("You don't have the permission to do this action");
			return false;
		}
		return true;
	}

	abstract void build();
}
