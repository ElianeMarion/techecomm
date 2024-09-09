package br.com.fiap.techecomm.products.controller;

import br.com.fiap.techecomm.products.ResponseResult;
import br.com.fiap.techecomm.products.dto.AddProduct;
import br.com.fiap.techecomm.products.dto.UpdateProductPrice;
import br.com.fiap.techecomm.products.models.Product;
import br.com.fiap.techecomm.products.security.UserAuthDetails;
import br.com.fiap.techecomm.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/products")
public class ProductAuthController {
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseResult<?>> addProduct(@RequestBody AddProduct addProduct){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(addProduct));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("update/price")
    public ResponseEntity<Product> updatePriceOnlyProduct(@RequestBody UpdateProductPrice data) {
        return ResponseEntity.status(HttpStatus.OK).body(
                productService.updatePriceProduct(data.getProductId(), data.getPrice())
        );
    }
}
