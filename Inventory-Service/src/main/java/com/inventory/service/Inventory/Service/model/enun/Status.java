package com.inventory.service.Inventory.Service.model.enun;

public enum Status {
    PROCESSING_PAYMENT,
    OUT_OF_STOCK,
    FAILED;

    public String getName() {
        return name();
    }
}
