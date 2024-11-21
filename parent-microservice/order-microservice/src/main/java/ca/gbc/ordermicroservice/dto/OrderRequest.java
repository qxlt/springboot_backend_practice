package ca.gbc.ordermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record OrderRequest(
        Long id,
        String orderNumber,
        String skuNumber,
        BigDecimal price,
        Integer quantity) {
}
