package project.service;

import project.annotation.Cacheable;

public class InventoryServiceImpl implements InventoryService {

    @Override
    public int checkStock(String sku) {
        System.out.println(sku + " stock checked.");
        return 0;
    }

    @Override
    @Cacheable
    public int reserveStock(String sku, int qty) {
        System.out.println(qty + " of " + sku + " has been reserved.");
        if (qty > 100) throw new IllegalStateException("Quantity must be less than 100");
        return qty;
    }
}
