package br.com.fiap.shoppingcart.consumer;

import br.com.fiap.shoppingcart.dto.Product;
import br.com.fiap.shoppingcart.dto.UpdateProductStock;
import br.com.fiap.shoppingcart.dto.UpdateStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "products", url = "${URL_PRODUCT}")
public interface ProductConsumer {

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable("id") Long id);

    @PutMapping("/products/{productId}/update-stock")
    Product updateProductStock(@PathVariable("productId") Long productId, @RequestBody UpdateStockRequest request);

    @PatchMapping("/update/stock")
    void incrementStockProduct(@RequestBody UpdateProductStock product);
}
