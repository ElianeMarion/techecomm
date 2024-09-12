package br.com.fiap.shoppingcart.repository;

import br.com.fiap.shoppingcart.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShoppingCartRepository extends JpaRepository<CartEntity, Long> {

    @Query("SELECT c FROM CartEntity c WHERE c.userId = :userId AND c.dateClose IS NULL")
    Optional<CartEntity> findUserId(Long userId);

    CartEntity findByUserId(Long userId);

    Optional<CartEntity> findCartByUserId(Long userId);
}
