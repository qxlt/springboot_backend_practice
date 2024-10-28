package ca.gbc.ordermicroservice.dto;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        String orderNumber,
        String skuNumber,
        BigDecimal price,
        Integer quantity) {
}
