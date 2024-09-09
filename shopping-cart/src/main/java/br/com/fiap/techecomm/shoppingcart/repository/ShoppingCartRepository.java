package br.com.fiap.techecomm.shoppingcart.repository;

import br.com.fiap.techecomm.shoppingcart.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<CartEntity, Long> {

    @Query("SELECT c FROM CartEntity c WHERE c.userId = :userId AND c.dateClose IS NULL")
    Optional<CartEntity> findUserId(Long userId);
}
