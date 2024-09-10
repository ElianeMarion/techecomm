package br.com.fiap.techecomm.shoppingcart.controller;

import br.com.fiap.techecomm.shoppingcart.ResponseResult;
import br.com.fiap.techecomm.shoppingcart.security.UserAuthDetails;
import br.com.fiap.techecomm.shoppingcart.security.UserAuthException;
import br.com.fiap.techecomm.shoppingcart.service.ShoppingCartService;
import br.com.fiap.techecomm.shoppingcart.vo.ItemCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @GetMapping("{userId}")
    public ResponseEntity<ResponseResult<?>> getCartUser(@PathVariable Long userId, Authentication authentication){
        UserAuthDetails.validUserIdAuth(authentication, userId).orElseThrow(UserAuthException::new);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.getCartBy(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseResult<>(null, e.getMessage()));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @PostMapping("/add-cart-item/{userId}")
    public ResponseEntity<ResponseResult<?>> getCartUser(@PathVariable Long userId,
                                                         @RequestBody ItemCartVO itemCartVO,
                                                         Authentication authentication){
        UserAuthDetails.validUserIdAuth(authentication, userId).orElseThrow(UserAuthException::new);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartService.addItemCart(userId, itemCartVO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseResult<>(null, e.getMessage()));
        }
    }
}
