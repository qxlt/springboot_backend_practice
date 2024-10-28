package ca.gbc.ordermicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ca.gbc.ordermicroservice.model.Order;
public interface OrderRepository extends JpaRepository<Order, Long> {
}
