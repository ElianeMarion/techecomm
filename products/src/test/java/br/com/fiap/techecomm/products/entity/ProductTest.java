package br.com.fiap.techecomm.products.entity;

import br.com.fiap.techecomm.products.enums.StatusProductEnum;
import br.com.fiap.techecomm.products.models.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductTest {
    @Test
    public void testProductConstructor() {
        // Arrange
        String name = "Laptop";
        String description = "High-end gaming laptop";
        BigDecimal price = new BigDecimal("1200.00");
        int quantityStock = 10;
        LocalDate updateDate = LocalDate.of(2024, 9, 1);
        StatusProductEnum status = StatusProductEnum.ATIVO;

        // Act
        Product product = new Product(name, description, price, quantityStock, updateDate, status);

        // Assert
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getDescription()).isEqualTo(description);
        assertThat(product.getPrice()).isEqualByComparingTo(price);
        assertThat(product.getQuantityStock()).isEqualTo(quantityStock);
        assertThat(product.getUpdateDate()).isEqualTo(updateDate);
        assertThat(product.getStatus()).isEqualTo(status);
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Product product = new Product();

        // Act
        product.setProductId(1L);
        product.setName("Smartphone");
        product.setDescription("Latest model smartphone");
        product.setPrice(new BigDecimal("999.99"));
        product.setQuantityStock(5);
        product.setUpdateDate(LocalDate.of(2024, 9, 2));
        product.setStatus(StatusProductEnum.INATIVO);

        // Assert
        assertThat(product.getProductId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("Smartphone");
        assertThat(product.getDescription()).isEqualTo("Latest model smartphone");
        assertThat(product.getPrice()).isEqualByComparingTo(new BigDecimal("999.99"));
        assertThat(product.getQuantityStock()).isEqualTo(5);
        assertThat(product.getUpdateDate()).isEqualTo(LocalDate.of(2024, 9, 2));
        assertThat(product.getStatus()).isEqualTo(StatusProductEnum.INATIVO);
    }

    @Test
    public void testBuilder() {
        // Arrange
        Product product = Product.builder()
                .name("Tablet")
                .description("10-inch screen tablet")
                .price(new BigDecimal("299.99"))
                .quantityStock(15)
                .updateDate(LocalDate.of(2024, 9, 3))
                .status(StatusProductEnum.ATIVO)
                .build();

        // Assert
        assertThat(product.getName()).isEqualTo("Tablet");
        assertThat(product.getDescription()).isEqualTo("10-inch screen tablet");
        assertThat(product.getPrice()).isEqualByComparingTo(new BigDecimal("299.99"));
        assertThat(product.getQuantityStock()).isEqualTo(15);
        assertThat(product.getUpdateDate()).isEqualTo(LocalDate.of(2024, 9, 3));
        assertThat(product.getStatus()).isEqualTo(StatusProductEnum.ATIVO);
    }
}
