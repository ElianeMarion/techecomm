package br.com.fiap.techecomm.products.exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound() {
        super("The product not found!");
    }

}
