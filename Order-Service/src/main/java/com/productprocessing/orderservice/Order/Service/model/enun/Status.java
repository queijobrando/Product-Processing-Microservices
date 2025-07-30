package com.productprocessing.orderservice.Order.Service.model.enun;

public enum Status {
    CREATED,              // Pedido criado
    INVENTORY_RESERVED,   // Estoque reservado
    PAYMENT_SUCCEEDED,    // Pagamento aprovado
    CONFIRMED,            // Pedido confirmado com sucesso
    FAILED
}
