package br.com.fiap.techecomm.products.dto;

import br.com.fiap.techecomm.products.enums.StatusProductEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProduct {

    private String name;
    private String description;
    private BigDecimal price;
    private int quantityStock;
    private LocalDate updateDate;
    private StatusProductEnum status;
}
