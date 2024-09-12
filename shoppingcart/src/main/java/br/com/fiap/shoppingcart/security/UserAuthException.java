package br.com.fiap.shoppingcart.security;

public class UserAuthException extends RuntimeException {

    public UserAuthException() {}

    public UserAuthException(String message) {
        super(message);
    }

}
