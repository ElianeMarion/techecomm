package br.com.fiap.techecomm.shoppingcart.security;

public class UserAuthException extends RuntimeException {

    public UserAuthException() {}

    public UserAuthException(String message) {
        super(message);
    }

}
