package com.toasternetwork.inventory;

import java.util.List;

/**
 * Main!
 */
public class Main {

    private static ProductDatabase inventory;
    private static EmployeeDatabase employee;
    private static StoreFront store;
    private static MainMenu mainMenu;
    private static Login loginMenu;

    private static Employee loggedIn;

    /**
     * Gets currently logged in
     * @return The logged in employee
     */
    public static Employee getLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the main logged user
     * @param employee The employee that is authenticated
     */
    public static void setLoggedIn(Employee employee) {
        loggedIn = employee;
    }

    /**
     * Gets the config for Inventory
     * @return The Inventory Database
     */
    public static ProductDatabase getInventory() {
        return inventory;
    }

    /**
     * Gets the config for Employees
     * @return The Employee Database
     */
    public static EmployeeDatabase getEmployees() {
        return employee;
    }

    /**
     * Gets the current Main Menu
     * @return The Main Menu
     */
    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * Gets the current Login Menu
     * @return The Login Menu
     */
    public static Login getLogin() {
        return loginMenu;
    }

    /**
     * Gets the instantiated Store
     * @return The loaded StoreFront
     */
    public static StoreFront getStore() {
        return store;
    }

    /**
     * The Main Entrypoint
     * @param args *Not used*
     */
    public static void main(String[] args) {
        System.out.println("Store Front v1.0");

        mainMenu = new MainMenu();
        loginMenu = new Login();

        inventory = new ProductDatabase("inventory.json");
        employee = new EmployeeDatabase("employee.json");

        store = new StoreFront(1, 2500000, mainMenu);
        List<Product> products = null;

        mainMenu.setStore(store);
        store.hire(Manager.getRoot());
        store.addRegisters(4);
        mainMenu.setRegisters(new RegisterMenu(store.getRegisters(), mainMenu));

        try {
            inventory.open();
            employee.open();
            products = inventory.readFile();
            List<Employee> employees = employee.readFile();
            employees.forEach(store::hire);

        } catch (Exception e) {
            System.out.println("The database is empty");
            e.printStackTrace();
        }

        Menulet<?> currentMenu = loginMenu;

        //store.hire("Adonis", "Deliannis", 1, 32, "Manager", Role.Administrator, Manager.getRoot());

        if(products != null) {
            for(Product p : products)
                store.addProduct(p);
            // products.forEach(store::addProduct);
        }
/*
        store.addProduct("9mm Pistol", "A 9mm Pistol that is not a HighPoint", 400d, 150);
        store.addProduct("Sword", "Link's sword from Twilight Princess", 200d, 1);
        store.addProduct("Light Armor", "Light Armor", 50d, 1000);
        store.addProduct("Heavy Armor", "Super duper heavy armor", 100d, 1000);
        store.addProduct("10HP Health", "10 Hit Points", 5d, 1000000);
*/
        while(true) {
            try {
                int option = currentMenu.choose();
                currentMenu = (Menulet<?>) currentMenu.execute(option);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
