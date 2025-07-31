package com.inventory.service.Inventory.Service.model.enun;

public enum Status {
    INVENTORY_RESERVED,
    INVENTORY_FAILED;

    public String getName() {
        return name();
    }
}
