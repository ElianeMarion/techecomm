package br.com.fiap.techecomm.shoppingcart;

import br.com.fiap.techecomm.shoppingcart.dto.ItemCartDTO;
import br.com.fiap.techecomm.shoppingcart.model.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseAddAll {
    private List<CartEntity> addedCartEntities;
    private List<ItemCartDTO> noAddedProducts;
}
