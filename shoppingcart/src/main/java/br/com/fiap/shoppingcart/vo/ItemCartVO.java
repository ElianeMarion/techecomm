package br.com.fiap.shoppingcart.vo;

import br.com.fiap.shoppingcart.model.ItemCartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCartVO {
    private Long productIdCart;
    private BigDecimal unitPriceProductCart;
    private int quantity;

    public static List<ItemCartVO> buildList(List<ItemCartEntity> items) {
        List<ItemCartVO> list = new ArrayList<>();
        items.forEach(item -> {
            ItemCartVO itemCartVO = new ItemCartVO();
            itemCartVO.setProductIdCart(item.getItemCartId());

            itemCartVO.setUnitPriceProductCart(item.getUnitPrice());
            list.add(itemCartVO);
        });
        return list;
    }
}
