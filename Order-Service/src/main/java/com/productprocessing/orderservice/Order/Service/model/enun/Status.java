package com.productprocessing.orderservice.Order.Service.model.enun;

public enum Status {
    CREATED("Order created"),
    INVENTORY_RESERVED("Inventory reserved"),
    OUT_OF_STOCK("Out of Stock"),
    PAYMENT_SUCCEEDED("Payment aproved"),
    PAYMENT_FAILED("Payment  failed"),
    CONFIRMED("Order confirmed"),
    FAILED("Fail");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
