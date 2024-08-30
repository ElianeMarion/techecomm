package br.com.fiap.techecomm.shoppingcart.dto;

import br.com.fiap.techecomm.shoppingcart.model.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long cartUserId;
    private List<ItemCartDTO> itemsCart = new ArrayList<>();

    public static CartDTO build(CartEntity cartEntity, List<ItemCartDTO> itemsCart) {
        CartDTO CartDTO = new CartDTO();
        CartDTO.setItemsCart(itemsCart);
        CartDTO.setCartUserId(cartEntity.getUserId());
        return CartDTO;
    }
}
