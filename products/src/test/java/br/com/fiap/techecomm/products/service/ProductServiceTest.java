package br.com.fiap.techecomm.products.service;

import br.com.fiap.techecomm.products.ResponseResult;
import br.com.fiap.techecomm.products.dto.AddProduct;
import br.com.fiap.techecomm.products.enums.StatusProductEnum;
import br.com.fiap.techecomm.products.exception.*;
import br.com.fiap.techecomm.products.models.Product;
import br.com.fiap.techecomm.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        // Arrange
        AddProduct addProduct = new AddProduct();
        addProduct.setName("Laptop");
        addProduct.setDescription("Gaming Laptop");
        addProduct.setPrice(new BigDecimal("1500"));
        addProduct.setQuantityStock(10);
        addProduct.setUpdateDate(LocalDate.now());
        addProduct.setStatus(StatusProductEnum.ATIVO);

        Product savedProduct = new Product(
                addProduct.getName(),
                addProduct.getDescription(),
                addProduct.getPrice(),
                addProduct.getQuantityStock(),
                LocalDate.now(),
                StatusProductEnum.ATIVO
        );

        when(productRepository.findByName(addProduct.getName())).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        ResponseResult<?> response = productService.addProduct(addProduct);

        // Assert
        assertThat(response.getResult()).isInstanceOf(Product.class);
        assertThat(response.getMessage()).isEqualTo("Added successfully!");
        verify(productRepository, times(1)).save(any(Product.class));
    }
    @Test
    void testFindProductById_NotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        try {
            productService.findProductById(productId);
        } catch (ProductNotFound ex) {
            assertThat(ex).isInstanceOf(ProductNotFound.class);
        }
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Product existingProduct = new Product(1L, "Smartphone", "Latest model", new BigDecimal("800"), 5, LocalDate.now(), StatusProductEnum.ATIVO);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        // Act
        Product updatedProduct = productService.updateProduct(existingProduct);

        // Assert
        assertThat(updatedProduct.getName()).isEqualTo("Smartphone");
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(existingProduct);
    }

}
