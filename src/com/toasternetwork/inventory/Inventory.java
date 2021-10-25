package com.toasternetwork.inventory;

import java.io.IOException;
import java.util.*;

/**
 * The Inventory of a Store
 */
public class Inventory extends Menulet<Object> {
	private final ArrayList<Product> products;
	private final MainMenu parent;
	private final Scanner input;

	/**
	 * New Inventory
	 * @param parent The Parent Menu
	 */
	public Inventory(MainMenu parent) {
		super();
		products = new ArrayList<>();
		this.parent = parent;
		input = new Scanner(System.in);
		build();
	}

	/**
	 * Commits to inventory
	 */
	private void commit() {
		try {
			Main.getInventory().writeContents(products);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a new product to inventory
	 * @param name The new name of the product
	 * @param description The description
	 * @param price The price
	 * @param quantity The quantity of the product
	 */
	public void addProduct(String name, String description, double price, long quantity) {
		Product p = new Product();
		p.setName(name);
		p.setDescription(description);
		p.setPrice(price);
		p.setQuantity(quantity);
		if(products.size() < 1)
			p.setSku(1);
		else
			p.setSku(products.get(products.size()-1).getSku() + 1);
		products.add(p);
		commit();
	}

	/**
	 * Adds an existing product to inventory; likely from an external database
	 * @param product The product
	 */
	public void addProduct(Product product) {
		products.add(product);
		commit();
	}

	/**
	 * Gets the SKU of the product by name
	 * @param productName The product by name
	 * @return The product's SKU
	 */
	public long getProductSku(String productName) {
		Optional<Product> prod = products.stream().filter(p -> Objects.equals(p.getName(), productName)).findFirst();
		if(prod.isEmpty()) return 0;

		return prod.get().getSku();
	}

	/**
	 * Gets product by SKU
	 * @param sku The SKU
	 * @return The product
	 */
	public Product getProduct(long sku) {
		Optional<Product> prod = products.stream().filter(p -> Objects.equals(p.getSku(), sku)).findFirst();
		if(prod.isEmpty()) return null;

		return prod.get();
	}

	/**
	 * Gets the entire inventory
	 * @return The entire inventory
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}

	/**
	 * Updates the product's quantity
	 * @param sku The SKU
	 * @param quantity The new quantity
	 */
	public void updateProductQuantity(long sku, long quantity) {
		Product p = getProduct(sku);
		if(p == null) return;

		p.setQuantity(quantity);
		commit();
	}

	/**
	 * Updates the product's price
	 * @param sku The SKU
	 * @param price The new price
	 */
	public void updateProductPrice(long sku, double price) {
		Product p = getProduct(sku);
		if(p == null) return;

		p.setPrice(price);
		commit();
	}

	/**
	 * Updates the product by SKU
	 * @param product The updated Product
	 */
	public void updateProduct(Product product) {
		int index = products.indexOf(getProduct(product.getSku()));
		products.set(index, product);
		commit();
	}

	@Override
	public void build() {
		super.menu.put("Add Product", m -> {
			if (!checkPermission())
				return this;

			System.out.print("Name: ");
			String name = input.nextLine();
			System.out.print("Description: ");
			String desc = input.nextLine();
			System.out.print("Price: ");
			double price = input.nextDouble();
			input.nextLine();
			System.out.print("Quantity: ");
			long quantity = input.nextLong();
			input.nextLine();

			addProduct(name, desc, price, quantity);

			return this;
		});
		super.menu.put("Modify Product", m -> {
			if (!checkPermission())
				return this;
			System.out.print("Sku: ");
			long sku = input.nextLong();
			return new ProductModification(m, getProduct(sku));
		});
		super.menu.put("Remove Product", m -> {
			if (!checkPermission())
				return this;
			System.out.print("Sku: ");
			long sku = input.nextLong();
			input.nextLine();
			Product pro = getProduct(sku);
			if(products.remove(pro)) {
				System.out.printf("The product \"%s\" has been successfully removed from inventory\n", pro.getName());
				commit();
			} else {
				System.out.printf("Could not remove \"%s\" successfully", pro.getName());
			}

			return this;
		});
		super.menu.put("Show Inventory", m -> {
			products.forEach(item -> System.out.println(item.toString()));
			return this;
		});
		super.menu.put("Main Menu", m -> parent);
	}
}
