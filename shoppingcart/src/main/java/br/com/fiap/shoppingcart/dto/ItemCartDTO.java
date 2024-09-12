package br.com.fiap.shoppingcart.dto;

import br.com.fiap.shoppingcart.model.ItemCartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class ItemCartDTO {
    private Long itemId;
    private int itemsTotal;

    private BigDecimal priceItem;
    private Product product;
    private Long itemCartId;



    public static List<ItemCartDTO> getItemsCartDTO(List<ItemCartEntity> itemsCartEntity) {
        List<ItemCartDTO> itemCartDTOList = new ArrayList<>();
        for (ItemCartEntity itemCartEntity : itemsCartEntity) {
            ItemCartDTO itemCartDTO = new ItemCartDTO();
            itemCartDTO.setItemId(itemCartEntity.getItemCartId());
            itemCartDTO.setItemsTotal(itemCartEntity.getTotalItems());
            itemCartDTO.setPriceItem(itemCartEntity.getUnitPrice());
            itemCartDTOList.add(itemCartDTO);
        }
        return itemCartDTOList;
    }
}
