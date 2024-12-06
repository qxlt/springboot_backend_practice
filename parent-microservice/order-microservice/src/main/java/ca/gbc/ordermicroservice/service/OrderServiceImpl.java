package ca.gbc.ordermicroservice.service;

import ca.gbc.ordermicroservice.dto.OrderRequest;
import ca.gbc.ordermicroservice.event.OrderPlacedEvent;
import ca.gbc.ordermicroservice.orderClient.InventoryClient;
import ca.gbc.ordermicroservice.repository.OrderRepository;
import ca.gbc.ordermicroservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        boolean isProductInStock;
        try {
            isProductInStock = inventoryClient.isInStock(orderRequest.skuNumber(), orderRequest.quantity());
        } catch (Exception e) {
            log.error("Failed to check inventory for SKU: " + orderRequest.skuNumber(), e);
            throw new RuntimeException("Inventory service unavailable");
        }


//        isProductInStock = inventoryClient.isInStock(orderRequest.skuNumber(), orderRequest.quantity());

        if(isProductInStock){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuNumber(orderRequest.skuNumber())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();

        orderRepository.save(order);

        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
        orderPlacedEvent.setOrderNumber(order.getOrderNumber());
        orderPlacedEvent.setEmail(orderRequest.userDetails().email());
        orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
        orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());
        log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
        kafkaTemplate.send("order-placed", orderPlacedEvent);
        log.info("End - Sending OrderPlacedEvent {} to Kafka order-placed", orderPlacedEvent);
        }else{
            throw new RuntimeException("Product with sku number " + orderRequest.skuNumber() + "is not in stock");
        }

    }
}
