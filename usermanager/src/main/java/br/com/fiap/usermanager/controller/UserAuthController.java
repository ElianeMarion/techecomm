package br.com.fiap.usermanager.controller;


import br.com.fiap.usermanager.dto.AuthUserRecord;
import br.com.fiap.usermanager.dto.UserRecord;
import br.com.fiap.usermanager.enums.RoleType;
import br.com.fiap.usermanager.model.UserLogin;
import br.com.fiap.usermanager.security.TokenService;
import br.com.fiap.usermanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class UserAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRecord userDTO){
        UserRecord savedUser = userService.save(userDTO, RoleType.ROLE_CUSTOMER);
        if  (savedUser  ==  null) {
            return ResponseEntity.badRequest().build();
        } else {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthUserRecord authUserRecord){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authUserRecord.login(),
                authUserRecord.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserLogin) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }
}
