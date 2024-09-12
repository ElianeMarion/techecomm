package br.com.fiap.shoppingcart.repository;


import br.com.fiap.shoppingcart.model.ItemCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingItemCartRepository extends JpaRepository<ItemCartEntity, Long> {

    @Query("SELECT i FROM ItemCartEntity i WHERE i.itemCartId = :itemCartId")
    Optional<ItemCartEntity> findByItemCardId(Long itemCartId);

    @Query("SELECT i FROM ItemCartEntity i inner join CartEntity c on i.cart.cartId = c.cartId WHERE c.cartId = :cartId")
    List<ItemCartEntity> findByCart_CartId(Long cartId);
}
