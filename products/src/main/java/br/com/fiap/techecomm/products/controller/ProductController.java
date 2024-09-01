package br.com.fiap.techecomm.products.controller;

import br.com.fiap.techecomm.products.ResponseResult;
import br.com.fiap.techecomm.products.dto.AddProduct;
import br.com.fiap.techecomm.products.dto.UpdateProductPrice;
import br.com.fiap.techecomm.products.models.Product;
import br.com.fiap.techecomm.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> listAllProducts() {
        return productService.listAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductById(id));
    }



}
