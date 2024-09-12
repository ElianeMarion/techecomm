package br.com.fiap.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantityStock;
    private LocalDate updateDate;

}
