package br.com.fiap.techecomm.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductStock {
    private Long productId;
    private int additionalStock;
}
