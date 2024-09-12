package br.com.fiap.shoppingcart.repository;

import br.com.fiap.shoppingcart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
