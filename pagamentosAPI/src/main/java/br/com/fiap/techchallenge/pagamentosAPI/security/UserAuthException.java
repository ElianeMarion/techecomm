package br.com.fiap.techchallenge.pagamentosAPI.security;

public class UserAuthException extends RuntimeException {

    public UserAuthException() {}

    public UserAuthException(String message) {
        super(message);
    }

}
