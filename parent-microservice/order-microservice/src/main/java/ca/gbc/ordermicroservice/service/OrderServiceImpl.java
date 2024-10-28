package ca.gbc.ordermicroservice.service;

import ca.gbc.ordermicroservice.dto.OrderRequest;
import ca.gbc.ordermicroservice.repository.OrderRepository;
import ca.gbc.ordermicroservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public void placeOrder(OrderRequest orderRequest) {

        Order order = Order.builder()
                .order_number(UUID.randomUUID().toString())
                .sku_number(orderRequest.sku_number())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();

        orderRepository.save(order);
    }
}
