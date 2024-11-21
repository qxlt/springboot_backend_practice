package ca.gbc.inventorymicroservice.service;

import ca.gbc.inventorymicroservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional
    public boolean isInStock(String skuNumber, Integer quantity) {
        return inventoryRepository.existsBySkuNumberAndQuantityGreaterThanEqual(skuNumber, quantity);
    }


}
