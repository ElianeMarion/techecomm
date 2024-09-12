package br.com.fiap.shoppingcart.controller;


import br.com.fiap.shoppingcart.ResponseResult;
import br.com.fiap.shoppingcart.model.CartEntity;
import br.com.fiap.shoppingcart.security.UserAuthDetails;
import br.com.fiap.shoppingcart.security.UserAuthException;
import br.com.fiap.shoppingcart.service.ShoppingCartService;
import br.com.fiap.shoppingcart.vo.ItemCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @PostMapping("/{userId}")
    public CartEntity saveItemCart(@PathVariable Long userId,
                                   @RequestBody ItemCartVO itemCartVO,
                                   Authentication authentication){
        UserAuthDetails.validUserIdAuth(authentication, userId).orElseThrow(UserAuthException::new);
        return shoppingCartService.saveItemCart(userId, itemCartVO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseResult<?>> getCartUser(@PathVariable Long userId, Authentication authentication){
        UserAuthDetails.validUserIdAuth(authentication, userId).orElseThrow(UserAuthException::new);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.findCartByUser(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseResult<>(null, e.getMessage()));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @PostMapping("/checkout/{userId}")
    public ResponseEntity<ResponseResult<?>> checkout(@RequestHeader("Authorization") String token, @PathVariable Long userId, Authentication authentication){
        UserAuthDetails.validUserIdAuth(authentication, userId).orElseThrow(UserAuthException::new);
        ResponseResult<?> result = new ResponseResult<>(shoppingCartService.generatePaymentCart(userId, token), "sucesso");
        return ResponseEntity.ok(result);
    }


}
