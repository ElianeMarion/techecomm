package br.com.fiap.shoppingcart;


import br.com.fiap.shoppingcart.dto.ItemCartDTO;
import br.com.fiap.shoppingcart.model.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseAddAll {
    private List<CartEntity> addedCartEntities;
    private List<ItemCartDTO> noAddedProducts;
}
