package com.models;

import java.util.List;

public interface InventoryDAO {
    public List<Inventory> findAllInventory();
    public void add(Inventory inventory);
    public void delete(String id);
    public Inventory get(String id);
    public void update(Inventory inventory);
    public List<Inventory> findInventory(String keyword);
    public List<Products> findAllProducts();
}
