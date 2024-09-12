package br.com.fiap.techchallenge.pagamentosAPI.model.dto;

import lombok.*;

import java.math.BigDecimal;

@ToString
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
