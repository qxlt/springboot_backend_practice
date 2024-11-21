package ca.gbc.inventorymicroservice.controller;

import ca.gbc.inventorymicroservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //    http://localhost:{port}/api/inventory?skuNumber=SKU001&quantity=10
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuNumber, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuNumber, quantity);
    }
}