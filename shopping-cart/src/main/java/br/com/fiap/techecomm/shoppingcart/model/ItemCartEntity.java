package br.com.fiap.techecomm.shoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Entity
@Data
@Builder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class ItemCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCartId;

    private Long cartId;

    private int totalItems;

    private String size;

    private String color;

    private BigDecimal unitPrice;

    public ItemCartEntity(Long itemCartId, int totalItems, String size, String color, BigDecimal unitPrice) {
        this.itemCartId = itemCartId;
        this.totalItems = totalItems;
        this.size = size;
        this.color = color;
        this.unitPrice = unitPrice;
    }

    public static ItemEntityBuilder builder() {
        return new ItemEntityBuilder();
    }

    public static class ItemEntityBuilder {

        private Long itemCartId;
        private int totalItems;
        private String size;
        private String color;
        private BigDecimal unitPrice;

        public ItemEntityBuilder setItemCardId(final Long itemCartId) {
            this.itemCartId = itemCartId;
            return this;
        }

        public ItemEntityBuilder setTotalItems(final int totalItems) {
            this.totalItems = totalItems;
            return this;
        }

        public ItemEntityBuilder setSize(final String size) {
            this.size = size;
            return this;
        }

        public ItemEntityBuilder setColor(final String color) {
            this.color = color;
            return this;
        }

        public ItemEntityBuilder setUnitPrice(final BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public ItemCartEntity build() {
            return new ItemCartEntity(itemCartId, totalItems, size, color, unitPrice);
        }

    }
}

