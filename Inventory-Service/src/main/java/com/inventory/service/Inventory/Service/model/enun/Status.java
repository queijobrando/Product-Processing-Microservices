package com.inventory.service.Inventory.Service.model.enun;

public enum Status {
    INVENTORY_RESERVED,
    OUT_OF_STOCK,
    FAILED;

    public String getName() {
        return name();
    }
}
