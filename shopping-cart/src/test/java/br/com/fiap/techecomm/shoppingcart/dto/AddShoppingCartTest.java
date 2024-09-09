package br.com.fiap.techecomm.shoppingcart.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AddShoppingCartTest {

    @Test
    void deveRetornarPreenchido() {
        // Arrange
        ItemCartDTO itemCartDTO = new ItemCartDTO();

        // Action
        itemCartDTO.setItemId(1L);
        itemCartDTO.setItemsTotal(1);

        // Assert
        Assertions.assertThat(itemCartDTO).isNotNull();
    }
}