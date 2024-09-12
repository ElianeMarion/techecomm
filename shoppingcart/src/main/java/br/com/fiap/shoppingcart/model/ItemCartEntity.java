package br.com.fiap.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Entity
@Data
@Builder(toBuilder = true)
@Jacksonized
@NoArgsConstructor
@Table(name = "item_cart")
public class ItemCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCartId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private CartEntity cart;

    private int totalItems;

    private Long product;

    private BigDecimal unitPrice;

    public ItemCartEntity(Long itemCartId, CartEntity cart, int totalItems, Long product, BigDecimal unitPrice) {
        this.itemCartId = itemCartId;
        this.cart = cart;
        this.totalItems = totalItems;
        this.product = product;
        this.unitPrice = unitPrice;
    }


}

