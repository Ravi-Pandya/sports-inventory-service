package com.Pandya.inventory;

import com.Pandya.entity.Inventory;
import com.Pandya.entity.Store;

import java.util.List;

public interface InventoryService {

    List<Inventory> getInventoryList();

    void addToList(Inventory inventory);

    void removeFromList(Inventory inventory);

    void addStore(String name, Store store);

    List<Store> getAllStore();
}
