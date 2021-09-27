package com.toasternetwork.examples;

public class Main {
    public static void main(String[] args) {
        System.out.println("Store Front v1.0");

        MainMenu menu = new MainMenu();

        StoreFront store = new StoreFront(1, 2500000, menu);
        menu.setStore(store);
        store.addRegisters(4);
        menu.setRegisters(new RegisterMenu(store.getRegisters(), menu));

        Menulet<?> currentMenu = menu;
        Manager mgr = new Manager(0);
        store.hire("Adonis", "Deliannis", 1, 32, "Manager", mgr);
        store.addProduct("9mm Pistol", "A 9mm Pistol that is not a HighPoint", 400d, 150);
        store.addProduct("Sword", "Link's sword from Twilight Princess", 200d, 1);
        store.addProduct("Light Armor", "Light Armor", 50d, 1000);
        store.addProduct("Heavy Armor", "Super duper heavy armor", 100d, 1000);
        store.addProduct("10HP Health", "10 Hit Points", 5d, 1000000);

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
