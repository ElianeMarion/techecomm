package br.com.fiap.techecomm.shoppingcart.service;

import br.com.fiap.techecomm.shoppingcart.ResponseResult;
import br.com.fiap.techecomm.shoppingcart.dto.CartDTO;
import br.com.fiap.techecomm.shoppingcart.dto.ItemCartDTO;
import br.com.fiap.techecomm.shoppingcart.exception.*;
import br.com.fiap.techecomm.shoppingcart.model.CartEntity;
import br.com.fiap.techecomm.shoppingcart.model.ItemCartEntity;
import br.com.fiap.techecomm.shoppingcart.repository.ShoppingCartRepository;
import br.com.fiap.techecomm.shoppingcart.repository.ShoppingItemCartRepository;
import br.com.fiap.techecomm.shoppingcart.vo.ItemCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingItemCartRepository shoppingItemCartRepository;

    public ResponseResult<CartDTO> getCartBy(Long userId) {
        CartEntity cartEntity = this.getCartByUserId(userId);
        List<ItemCartEntity> itemsCartEntity = shoppingItemCartRepository.findByCartId(cartEntity.getCartId());
        return new ResponseResult<>(CartDTO.build(cartEntity, ItemCartDTO.getItemsCartDTO(itemsCartEntity)), "Find successfully!");
    }

    public ResponseResult<?> addItemCart(Long userId, ItemCartVO itemCartVO) {
        AtomicReference<CartEntity> cartEntity = new AtomicReference<>();
        try {
            cartEntity.set(this.getCartByUserId(userId));
        } catch (RuntimeException e) {
            cartEntity.set(createCartEntity(userId));
            shoppingCartRepository.save(cartEntity.get());
        }
        AtomicReference<ItemCartEntity> itemCartEntity = new AtomicReference<>();
        List<ItemCartEntity> itemsCartEntity = shoppingItemCartRepository.findByCartId(cartEntity.get().getCartId());

        if (!itemsCartEntity.isEmpty()) {
            itemsCartEntity.forEach(item -> {
                if (
                        Objects.equals(item.getItemCartId(), itemCartVO.getProductIdCart()) &&
                                Objects.equals(item.getColor(), itemCartVO.getColorProductCart()) &&
                                Objects.equals(item.getSize(), itemCartVO.getSizeProductCart())
                ){
                    item.setTotalItems(item.getTotalItems() + 1);
                    shoppingItemCartRepository.save(item);
                    itemCartEntity.set(item);
                } else {
                    addItemCart(itemCartVO, itemsCartEntity, itemCartEntity, cartEntity);
                }
            });
        } else {
            addItemCart(itemCartVO, itemsCartEntity, itemCartEntity, cartEntity);
        }

        CartEntity cartEntitySaved = shoppingCartRepository.save(cartEntity.get());

        return new ResponseResult<>(
            CartDTO.build(cartEntitySaved, ItemCartDTO.getItemsCartDTO(itemsCartEntity)), "add successfully!"
        );
    }

    private void addItemCart(ItemCartVO itemCartVO, List<ItemCartEntity> itemsCartEntity, AtomicReference<ItemCartEntity> itemCartEntity, AtomicReference<CartEntity> cartEntity) {
        ItemCartEntity.ItemEntityBuilder builder = ItemCartEntity.builder();
        builder
                .setTotalItems(itemsCartEntity.size() + 1)
                .setColor(itemCartVO.getColorProductCart())
                .setSize(itemCartVO.getSizeProductCart())
                .setUnitPrice(itemCartVO.getUnitPriceProductCart());
        itemCartEntity.set(builder.build());
        itemCartEntity.get().setCartId(cartEntity.get().getCartId());

        ItemCartEntity itemCartSaved = shoppingItemCartRepository.save(itemCartEntity.get());

        itemsCartEntity.add(itemCartSaved);
    }

    private static CartEntity createCartEntity(Long userId) {
        CartEntity cartEntity;
        cartEntity = new CartEntity();
        cartEntity.setUserId(userId);
        cartEntity.setDateOpen(LocalDate.now());
        return cartEntity;
    }

    private CartEntity getCartByUserId(Long userId) {
        return shoppingCartRepository.findUserId(userId).orElseThrow(CartByUserIdNotfound::new);
    }
}
