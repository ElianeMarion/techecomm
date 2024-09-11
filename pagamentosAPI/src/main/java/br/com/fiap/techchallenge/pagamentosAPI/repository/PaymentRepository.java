package br.com.fiap.techchallenge.pagamentosAPI.repository;

import br.com.fiap.techchallenge.pagamentosAPI.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
