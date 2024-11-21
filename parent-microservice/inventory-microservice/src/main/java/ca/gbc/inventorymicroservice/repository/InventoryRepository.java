package ca.gbc.inventorymicroservice.repository;

import ca.gbc.inventorymicroservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsBySkuNumberAndQuantityGreaterThanEqual(String skuNumber, Integer quantity);
//    boolean existsBySkuNumberAndQuantityGreaterThanEqual(String skuNumber, Integer quantity);
}
