package br.com.fiap.techecomm.products.service;

import br.com.fiap.techecomm.products.ResponseResult;
import br.com.fiap.techecomm.products.dto.AddProduct;
import br.com.fiap.techecomm.products.enums.StatusProductEnum;
import br.com.fiap.techecomm.products.exception.*;
import br.com.fiap.techecomm.products.models.Product;
import br.com.fiap.techecomm.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseResult<?> addProduct(AddProduct newProduct){
        try{
            var productFound = productRepository.findByName(newProduct.getName());
            Product product = new Product(
                    newProduct.getName(),
                    newProduct.getDescription(),
                    newProduct.getPrice(),
                    newProduct.getQuantityStock(),
                    LocalDate.now(),
                    StatusProductEnum.ATIVO
                );
            return new ResponseResult<>(productRepository.save(product), "Added successfully!");

        }catch (ProdutcFoundException e){
            return new ResponseResult<>(newProduct, e.getMessage());
        }
    }
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFound::new);
    }

    public Product updateProduct(Product product) {
        Product productFound = productRepository.findById(product.getProductId()).orElseThrow(ProductNotFound::new);
        productFound.setName(product.getName());
        productFound.setDescription(product.getDescription());
        productFound.setQuantityStock(product.getQuantityStock());
        productFound.setPrice(product.getPrice());
        productFound.setUpdateDate(LocalDate.now());
        productFound.setStatus(product.getStatus());
        return productRepository.save(productFound);
    }

    public Product updatePriceProduct(Long productId, BigDecimal price) {
        Product product = this.findProductById(productId);
        product.setPrice(price);
        product.setUpdateDate(LocalDate.now());
        this.updateProduct(product);
        return product;
    }


    public Product hasProductInStock(Long productId, int totalRequired) {
        Product product = this.findProductById(productId);
        if (product.getQuantityStock() < totalRequired) throw new NoStockProduct(totalRequired);
        return product;
    }

    public ResponseResult<Boolean> reserveProduct(Long productId, int totalRequired) {
        try {
            Product product = this.hasProductInStock(productId, totalRequired);
            product.setQuantityStock(product.getQuantityStock() - totalRequired);
            this.updateStockProduct(product);
            return new ResponseResult<>(true, "Success reserved!");
        } catch (RuntimeException e) {
            return new ResponseResult<>(false, e.getMessage());
        }
    }

    private Product updateStockProduct(Long productId, int quantityStock) {
        Product product = this.findProductById(productId);
        product.setQuantityStock(quantityStock);
        this.updateProduct(product);
        return product;
    }

    private void updateStockProduct(Product product) {
        Product productUpdated = this.updateStockProduct(product.getProductId(), product.getQuantityStock());
        System.out.println("Stock updated: " + productUpdated);
    }

}
