package br.com.fiap.techchallenge.pagamentosAPI.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {

    private Long orderId;
    private int quantity;
    private String payerName;
    private BigDecimal price;
    private Long userId;

}
