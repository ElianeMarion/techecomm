package br.com.fiap.shoppingcart.consumer;

import br.com.fiap.shoppingcart.dto.PaymentResponse;
import br.com.fiap.shoppingcart.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payments", url = "${URL_PAYMENT}")
public interface PaymentConsumer {

    @PostMapping()
    PaymentResponse createPayment(@RequestHeader HttpHeaders headers, @RequestBody Order order);
}
