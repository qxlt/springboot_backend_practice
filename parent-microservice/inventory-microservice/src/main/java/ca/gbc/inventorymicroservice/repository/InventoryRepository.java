package ca.gbc.inventorymicroservice.repository;

import ca.gbc.inventorymicroservice.model.inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<inventory, Long> {
    boolean existsBySkuNumberAndQuantityGreaterThanEqual(String sku_number, Integer quantity);

//    boolean findBySkuCode(String skuCode);
}
