package br.com.fiap.shoppingcart.service;


import br.com.fiap.shoppingcart.ResponseResult;
import br.com.fiap.shoppingcart.consumer.PaymentConsumer;
import br.com.fiap.shoppingcart.consumer.ProductConsumer;
import br.com.fiap.shoppingcart.dto.*;
import br.com.fiap.shoppingcart.exceptions.CartNotFoundException;
import br.com.fiap.shoppingcart.exceptions.ProductNotFoundException;
import br.com.fiap.shoppingcart.model.CartEntity;
import br.com.fiap.shoppingcart.model.ItemCartEntity;
import br.com.fiap.shoppingcart.model.Order;
import br.com.fiap.shoppingcart.repository.OrderRepository;
import br.com.fiap.shoppingcart.repository.ShoppingCartRepository;
import br.com.fiap.shoppingcart.repository.ShoppingItemCartRepository;
import br.com.fiap.shoppingcart.vo.ItemCartVO;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingItemCartRepository shoppingItemCartRepository;

    @Autowired
    private OrderRepository orderRepository;

    private final ProductConsumer productConsumer;
    private final PaymentConsumer paymentConsumer;

    public ShoppingCartService(ProductConsumer productConsumer, PaymentConsumer paymentConsumer) {
        this.productConsumer = productConsumer;
        this.paymentConsumer = paymentConsumer;
    }

    public ResponseResult<CartDTO> findCartByUser(Long userId){
        CartEntity cartEntity =  shoppingCartRepository.findByUserId(userId);
        List<ItemCartEntity> itemsCartEntity = shoppingItemCartRepository.findByCart_CartId(cartEntity.getCartId());
        cartEntity.setItemsCartEntity(itemsCartEntity);

        return new ResponseResult<>(CartDTO.build(cartEntity), "Find successfully!");
    }

    public Optional<CartEntity> findCartByUserId(Long userId){
        CartEntity cartEntity =  shoppingCartRepository.findCartByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("cart not found"));

        return Optional.ofNullable(cartEntity);
    }


    private CartEntity createCartEntity(Long userId) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserId(userId);
        cartEntity.setDateOpen(LocalDate.now());
        cartEntity = shoppingCartRepository.save(cartEntity);
        return cartEntity;
    }

    public CartEntity saveItemCart(Long userId, ItemCartVO itemVO){
        CartEntity cart;
        try{
            cart = shoppingCartRepository.findCartByUserId(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        }catch (EntityNotFoundException ex){
            cart = createCartEntity(userId);
        }
        List<ItemCartEntity> itens = shoppingItemCartRepository.findByCart_CartId(cart.getCartId());
        Product product = productConsumer.getProductById(itemVO.getProductIdCart()).getBody();
        if(product == null)
            throw new ProductNotFoundException("Product not found.");
        if (product.getQuantityStock() < itemVO.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock available");
        }
        log.info("Product " + product.getName() + product.getPrice());
        ItemCartEntity item = new ItemCartEntity();
        item.setProduct(product.getProductId());
        item.setCart(cart);
        item.setTotalItems(itemVO.getQuantity());
        item.setUnitPrice(product.getPrice());
        shoppingItemCartRepository.save(item);
        itens.add(item);
        return cart;
    }
    /*
    public CartEntity addItemToCart(Long userId, Long productId, Integer quantity) {
        CartEntity cart;
       try {
           cart = shoppingCartRepository.findCartByUserId(userId)
                   .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
       }catch (EntityNotFoundException ex){
           cart = createCartEntity(userId);
       }

        Product product = productConsumer.getProductById(productId).getBody();
        if(product == null)
            throw new ProductNotFoundException("Product not found.");

        if (product.getQuantityStock() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        ItemCartEntity itemCart = new ItemCartEntity();

        itemCart.setCart(cart);
        itemCart.setProduct(productId);
        itemCart.setUnitPrice(product.getPrice());
        itemCart.setTotalItems(quantity);

        shoppingItemCartRepository.save(itemCart);

        // Atualiza o estoque no microsserviço de produto
        UpdateStockRequest stockRequest = new UpdateStockRequest();
        stockRequest.setQuantity(quantity);
        productConsumer.updateProductStock(productId, stockRequest);

        return cart;
    }

    public ResponseResult<?> addItemCart(Long userId, ItemCartVO itemCartVO) {
        CartEntity cartEntity = new CartEntity();
        try {
            cartEntity = (this.findCartByUserId(userId).get());
        } catch (RuntimeException e) {
            cartEntity = (createCartEntity(userId));
            cartEntity = shoppingCartRepository.save(cartEntity);
        }

        List<ItemCartEntity> itemsCartEntity = shoppingItemCartRepository.findByCart_CartId(cartEntity.getCartId());
        ItemCartEntity item = new ItemCartEntity();
        item.setCart(cartEntity);
        item.setTotalItems(itemsCartEntity.size() + 1);
        item.setUnitPrice(itemCartVO.getUnitPriceProductCart());
        item.setProduct(itemCartVO.getProductIdCart());

       // itemCartEntity.get().setCartId(cartEntity.get().getCartId());

        ItemCartEntity itemCartSaved = shoppingItemCartRepository.save(item);

        itemsCartEntity.add(itemCartSaved);
        return new ResponseResult<>(itemCartSaved, "Add successfully");
    }*/

    public void canceledCartReturnStock(CartEntity cart){
        UpdateProductStock productStock = new UpdateProductStock();
        cart.getItemsCartEntity().forEach(i-> {
            productStock.setProductId(i.getProduct());
            productStock.setAdditionalStock(i.getTotalItems());
            this.productConsumer.incrementStockProduct(productStock);
        });
    }

    /**
     * O método devolve o valorTotal do carrinho.
     * @return double
     * @param cart
     *
     * */
    public BigDecimal totalCartValue(CartEntity cart){
        BigDecimal price = BigDecimal.ZERO;
        for(ItemCartEntity item: cart.getItemsCartEntity()){
            price = price.add(item.getUnitPrice());
        }
        return price;
    }

    public PaymentResponse generatePaymentCart(Long userId, String token){
        CartEntity cart = shoppingCartRepository.findCartByUserId(userId).orElseThrow(CartNotFoundException::new);
        List<ItemCartEntity> items = shoppingItemCartRepository.findByCart_CartId(cart.getCartId());
        BigDecimal priceTotal = totalCartValue(cart);
        Order order = new Order(userId, items.size(), priceTotal);
        order = orderRepository.save(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, token);
        PaymentResponse paymentResponse = paymentConsumer.createPayment(headers, order);
        shoppingCartRepository.delete(cart);
        return paymentResponse;
    }


}
