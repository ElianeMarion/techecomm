package br.com.fiap.shoppingcart.config;

import br.com.fiap.shoppingcart.ResponseResult;
import br.com.fiap.shoppingcart.exceptions.CartNotFoundException;
import br.com.fiap.shoppingcart.security.UserAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(UserAuthException.class)
    public ResponseEntity<?> errorUserAuthException(UserAuthException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<?> errorUserAuthException(AuthorizationDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<?> errorUserAuthException(CartNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseResult<>(null, "Carrinho não localizado"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> errorUserAuthException(Exception e) {
        return ResponseEntity.internalServerError().build();
    }

}
