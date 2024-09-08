package br.com.fiap.usermanager.controller.exception;

public class ControllerNotFoundException extends RuntimeException{
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
