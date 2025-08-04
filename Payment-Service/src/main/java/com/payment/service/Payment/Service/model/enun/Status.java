package com.payment.service.Payment.Service.model.enun;

public enum Status {
    PAYMENT_SUCCEEDED,
    PROCESSING_PAYMENT,
    OUT_OF_STOCK,
    PAYMENT_FAILED,
    FAILED;

    public String getName() {
        return name();
    }

}
