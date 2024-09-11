package br.com.fiap.techchallenge.pagamentosAPI.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class PaymentResponse {
    private Long idPagamento;
}
