package br.com.fiap.usermanager.controller;


import br.com.fiap.usermanager.dto.AuthUserRecord;
import br.com.fiap.usermanager.dto.UserRecord;
import br.com.fiap.usermanager.enums.RoleType;
import br.com.fiap.usermanager.model.Role;
import br.com.fiap.usermanager.model.UserLogin;
import br.com.fiap.usermanager.repository.RoleRepository;
import br.com.fiap.usermanager.repository.UserRepository;
import br.com.fiap.usermanager.security.TokenService;
import br.com.fiap.usermanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auth")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    private TokenService tokenService;

    /*
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    */

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
        /*
        if(this.userRepository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        List<Role> roles = roleRepository.findAll();
        roles = roles.stream()
                .filter(role -> role.getRoleName().equals(RoleType.ROLE_CUSTOMER))
                .toList();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserLogin newUser = new UserLogin(data.login(),
                data.name(), data.email(),
                encryptedPassword, roles);
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
        */
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
