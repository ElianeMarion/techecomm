package br.com.fiap.techecomm.shoppingcart.vo;

import br.com.fiap.techecomm.shoppingcart.model.ItemCartEntity;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.techecomm.shoppingcart.model.CartEntity;

@Data
public class CartVO {

    private Long cartUserId;
    private List<ItemCartVO> itemsCart;
    private LocalDate dateOpenCart;

   public static CartVO build(CartEntity cartEntity, List<ItemCartEntity> itemsCartEntity) {
       CartVO cartVO = new CartVO();
       cartVO.cartUserId = cartEntity.getUserId();
       cartVO.itemsCart = ItemCartVO.buildList(itemsCartEntity);
       cartVO.dateOpenCart = cartEntity.getDateOpen();
       return cartVO;
   }
}
