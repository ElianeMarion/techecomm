package br.com.fiap.shoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCartEntity> itemsCartEntity = new ArrayList<>();

    private LocalDate dateOpen;

    private LocalDate dateClose;

}

