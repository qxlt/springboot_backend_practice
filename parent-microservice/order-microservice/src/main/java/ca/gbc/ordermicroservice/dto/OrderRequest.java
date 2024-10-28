package ca.gbc.ordermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record OrderRequest(
        Long id,
        String order_number,
        String sku_number,
        BigDecimal price,
        Integer quantity) {
}
