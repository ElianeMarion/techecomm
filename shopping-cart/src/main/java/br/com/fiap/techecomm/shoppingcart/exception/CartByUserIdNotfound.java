package br.com.fiap.techecomm.shoppingcart.exception;

public class CartByUserIdNotfound extends RuntimeException {
    public CartByUserIdNotfound() {
        super("The user not have open cart.");
    }
}
