package br.com.fiap.techchallenge.pagamentosAPI.service;

import br.com.fiap.techchallenge.pagamentosAPI.model.Payment;
import br.com.fiap.techchallenge.pagamentosAPI.model.PaymentResponse;
import br.com.fiap.techchallenge.pagamentosAPI.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    public PaymentResponse registraPagamento(Payment payment){
        return PaymentResponse.builder().idPagamento(paymentRepository.save(payment).getId()).build();
    }



}
