package ca.gbc.inventorymicroservice.service;

import org.springframework.stereotype.Service;

public interface InventoryService {
    boolean isInStock(String skuNumber, Integer quantity);
}
