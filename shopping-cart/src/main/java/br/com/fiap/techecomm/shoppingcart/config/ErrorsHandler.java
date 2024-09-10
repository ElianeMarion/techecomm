package br.com.fiap.techecomm.shoppingcart.config;

import br.com.fiap.techecomm.shoppingcart.security.UserAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(UserAuthException.class)
    public ResponseEntity<?> errorUserAuthException(UserAuthException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
