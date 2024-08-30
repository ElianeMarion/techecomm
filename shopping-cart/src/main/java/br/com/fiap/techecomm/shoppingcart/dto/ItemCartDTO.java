package br.com.fiap.techecomm.shoppingcart.dto;

import br.com.fiap.techecomm.shoppingcart.model.CartEntity;
import br.com.fiap.techecomm.shoppingcart.model.ItemCartEntity;
import br.com.fiap.techecomm.shoppingcart.vo.CartVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCartDTO {
    private Long itemId;
    private int itemsTotal;
    private String sizeItem;
    private String colorItem;
    private BigDecimal priceItem;

    public static List<ItemCartDTO> getItemsCartDTO(List<ItemCartEntity> itemsCartEntity) {
        List<ItemCartDTO> itemCartDTOList = new ArrayList<>();
        for (ItemCartEntity itemCartEntity : itemsCartEntity) {
            ItemCartDTO itemCartDTO = new ItemCartDTO();
            itemCartDTO.setItemId(itemCartEntity.getItemCartId());
            itemCartDTO.setColorItem(itemCartEntity.getColor());
            itemCartDTO.setSizeItem(itemCartEntity.getSize());
            itemCartDTO.setItemsTotal(itemCartEntity.getTotalItems());
            itemCartDTO.setPriceItem(itemCartEntity.getUnitPrice());
            itemCartDTOList.add(itemCartDTO);
        }
        return itemCartDTOList;
    }
}
