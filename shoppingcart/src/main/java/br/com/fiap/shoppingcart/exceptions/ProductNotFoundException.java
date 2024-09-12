package br.com.fiap.shoppingcart.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String s) {
        super(s);
    }
}
