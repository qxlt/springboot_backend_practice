package ca.gbc.ordermicroservice.service;

import ca.gbc.ordermicroservice.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);

}
