package br.com.fiap.techecomm.products.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReserveProductStockTest {

    @Test
    public void testReserveProductStockSettersAndGetters() {
        ReserveProductStock reserveProduct = new ReserveProductStock();

        // Act
        reserveProduct.setProductId(1L);
        reserveProduct.setQuantityRequired(100);

        assertThat(reserveProduct.getQuantityRequired()).isEqualTo(100);

    }
}
