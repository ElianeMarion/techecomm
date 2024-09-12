package br.com.fiap.shoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Long userId;

    private Integer quantity;

    private BigDecimal price;


    public Order(Long userId, Integer quantity, BigDecimal price) {
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
    }
}
