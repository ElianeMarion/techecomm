package br.com.fiap.techecomm.products.dto;

import br.com.fiap.techecomm.products.enums.StatusProductEnum;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddProductTest {

    @Test
    public void testAddProductSettersAndGetters() {
        // Arrange
        AddProduct addProduct = new AddProduct();

        // Act
        addProduct.setName("Smart TV");
        addProduct.setDescription("50-inch 4K UHD Smart TV");
        addProduct.setPrice(new BigDecimal("799.99"));
        addProduct.setQuantityStock(20);
        addProduct.setUpdateDate(LocalDate.of(2024, 9, 4));
        addProduct.setStatus(StatusProductEnum.ATIVO);

        // Assert
        assertThat(addProduct.getName()).isEqualTo("Smart TV");
        assertThat(addProduct.getDescription()).isEqualTo("50-inch 4K UHD Smart TV");
        assertThat(addProduct.getPrice()).isEqualByComparingTo(new BigDecimal("799.99"));
        assertThat(addProduct.getQuantityStock()).isEqualTo(20);
        assertThat(addProduct.getUpdateDate()).isEqualTo(LocalDate.of(2024, 9, 4));
        assertThat(addProduct.getStatus()).isEqualTo(StatusProductEnum.ATIVO);
    }
}
