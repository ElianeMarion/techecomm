package br.com.fiap.shoppingcart.dto;

import br.com.fiap.shoppingcart.model.CartEntity;
import br.com.fiap.shoppingcart.model.ItemCartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class CartDTO {
    private CartEntity cart;
    private Long cartUserId;
    private List<ItemCartEntity> itemsCart = new ArrayList<>();

    public static CartDTO build(CartEntity cartEntity) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setItemsCart(cartEntity.getItemsCartEntity());
        cartDTO.setCartUserId(cartEntity.getUserId());
        cartDTO.setCart(cartEntity);
        return cartDTO;
    }
}
