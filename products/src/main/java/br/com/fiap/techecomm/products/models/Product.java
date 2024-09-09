package br.com.fiap.techecomm.products.models;

import br.com.fiap.techecomm.products.enums.StatusProductEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    @Column(name = "quantity_stock")
    private int quantityStock;
    @Column(name = "update_date")
    private LocalDate updateDate;
    @Enumerated(EnumType.STRING)
    private StatusProductEnum status;

    public Product(String name, String description, BigDecimal price,
                   int quantityStock, LocalDate updateDate, StatusProductEnum status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityStock = quantityStock;
        this.updateDate = updateDate;
        this.status = status;
    }
}
