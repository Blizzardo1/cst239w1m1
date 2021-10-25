package com.toasternetwork.inventory;

import java.util.Scanner;

/**
 * Modification menu for a product
 */
public class ProductModification extends Menulet<Object> {
	private final Scanner input;
	private final Menulet<?> parentMenu;
	private Product product;

	/**
	 * A new modification instance for a product
	 * @param parentMenu The main menu to return to
	 * @param product The product to be modified
	 */
	public ProductModification(Menulet<?> parentMenu, Product product) {
		super();
		this.parentMenu = parentMenu;
		this.product = product;
		this.input = new Scanner(System.in);
		build();
	}

	@Override
	void build() {
		if(isBuilt) return;

		menu.put("Name", m -> {
			System.out.print("New name: ");
			String name = input.nextLine();
			product.setName(name);
			Main.getStore().updateProduct(product);
			System.out.println("Name set");
			return parentMenu;
		});
		menu.put("Description", m -> {
			System.out.print("New Description: ");
			String description = input.nextLine();
			product.setDescription(description);
			Main.getStore().updateProduct(product);
			System.out.println("Description set");
			return parentMenu;
		});
		menu.put("Price", m -> {
			System.out.print("New Price: ");
			double price = input.nextDouble();
			product.setPrice(price);
			Main.getStore().updateProduct(product);
			System.out.println("Price set");
			return parentMenu;
		});
	}
}
