package project.service;

public interface InventoryService {
    int checkStock(String sku);
    int reserveStock(String sku, int qty);
}
