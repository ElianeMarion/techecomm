package br.com.fiap.shoppingcart.dto;

import br.com.fiap.shoppingcart.model.CartEntity;
import br.com.fiap.shoppingcart.model.ItemCartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private CartEntity cartEntity;
    private List<ItemCartEntity> itemCartEntity;
}
