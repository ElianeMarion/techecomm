package br.com.fiap.techecomm.shoppingcart.vo;

import br.com.fiap.techecomm.shoppingcart.model.ItemCartEntity;
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
    private String sizeProductCart;
    private String colorProductCart;
    private BigDecimal unitPriceProductCart;

    public static List<ItemCartVO> buildList(List<ItemCartEntity> items) {
        List<ItemCartVO> list = new ArrayList<>();
        items.forEach(item -> {
            ItemCartVO itemCartVO = new ItemCartVO();
            itemCartVO.setProductIdCart(item.getItemCartId());
            itemCartVO.setColorProductCart(item.getColor());
            itemCartVO.setSizeProductCart(item.getSize());
            itemCartVO.setUnitPriceProductCart(item.getUnitPrice());
            list.add(itemCartVO);
        });
        return list;
    }
}
