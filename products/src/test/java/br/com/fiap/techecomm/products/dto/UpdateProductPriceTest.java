package br.com.fiap.techecomm.products.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateProductPriceTest {

    @Test
    public void testUpdateProductPriceSettersAndGetters() {
        UpdateProductPrice updateProduct = new UpdateProductPrice();

        // Act
        updateProduct.setProductId(1L);
        updateProduct.setPrice(new BigDecimal("100"));

        assertThat(updateProduct.getPrice()).isEqualTo(BigDecimal.valueOf(100));

    }
}
