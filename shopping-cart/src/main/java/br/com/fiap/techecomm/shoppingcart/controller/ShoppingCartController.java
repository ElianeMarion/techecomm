package br.com.fiap.techecomm.shoppingcart.controller;

import br.com.fiap.techecomm.shoppingcart.ResponseResult;
import br.com.fiap.techecomm.shoppingcart.service.ShoppingCartService;
import br.com.fiap.techecomm.shoppingcart.vo.ItemCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("{userId}")
    public ResponseEntity<ResponseResult<?>> getCartUser(@PathVariable Long userId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.getCartBy(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseResult<>(null, e.getMessage()));
        }
    }

    @PostMapping("/add-cart-item/{userId}")
    public ResponseEntity<ResponseResult<?>> getCartUser(@PathVariable Long userId, @RequestBody ItemCartVO itemCartVO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartService.addItemCart(userId, itemCartVO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseResult<>(null, e.getMessage()));
        }
    }
}
