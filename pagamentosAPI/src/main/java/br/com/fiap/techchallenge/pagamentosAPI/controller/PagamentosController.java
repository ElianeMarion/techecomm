package br.com.fiap.techchallenge.pagamentosAPI.controller;

import br.com.fiap.techchallenge.pagamentosAPI.model.Payment;
import br.com.fiap.techchallenge.pagamentosAPI.model.PaymentResponse;
import br.com.fiap.techchallenge.pagamentosAPI.model.dto.PaymentDTO;
import br.com.fiap.techchallenge.pagamentosAPI.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("pagamentos")
public class PagamentosController {

    private final PaymentService paymentService;

    @PostMapping
    private ResponseEntity<PaymentResponse> registraPagamento(@RequestBody PaymentDTO paymentDTO){
        var paymentSalvo = paymentService.registraPagamento(Payment.builder()
                        .price(paymentDTO.getPrice())
                        .payerName(paymentDTO.getPayerName())
                        .quantity(paymentDTO.getQuantity())
                        .orderId(paymentDTO.getOrderId())
                        .build());

        return new ResponseEntity<>(paymentSalvo, HttpStatus.CREATED);
    }

}
