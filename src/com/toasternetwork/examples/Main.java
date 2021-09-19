package com.toasternetwork.examples;

public class Main {

    public static void main(String[] args) {
        System.out.println("Store Front v1.0");

        StoreFront store = new StoreFront(1, 2500000);
        store.addRegisters(4);

        Manager mgr = new Manager(0);
        store.hire("Adonis", "Deliannis", 1, 32, "Manager", mgr);
        store.addProduct("Apple", "A Basic Apple", 0.44d, 150);
        store.addProduct("Peach", "A Basic Peach", 0.52d, 200);

        Register r = store.getRegister(0);

        r.addProduct(store.getProduct(1), 12 );

        r.calculateTotal();
        System.out.println("Removing 6 Apples, adding 4 peaches");
        r.updateProductQuantity(1, 6);
        r.addProduct(store.getProduct(2), 4);

        r.calculateTotal();


        double tendered = 10;
        double change = r.receivePayment(tendered);
        System.out.printf("Amount tendered: $%.2f\nYour change is $%.2f", tendered, change);

        System.out.println("\n\n\n");
        r.addProduct(store.getProduct(1), 12 );
        r.addProduct(store.getProduct(2), 4);
        r.calculateTotal();
        r.cancelOrder();
        r.calculateTotal();
    }
}
