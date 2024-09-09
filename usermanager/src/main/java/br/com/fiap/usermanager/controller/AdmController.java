package br.com.fiap.usermanager.controller;

import br.com.fiap.usermanager.dto.UserRecord;
import br.com.fiap.usermanager.enums.RoleType;
import br.com.fiap.usermanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/adm")
public class AdmController {
    private final UserService userService;

    @Autowired
    public AdmController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserRecord> findById(@PathVariable Long userId) {
        UserRecord userRecord = userService.findById(userId);
        return ResponseEntity.ok(userRecord);
    }

    @PostMapping
    public ResponseEntity<UserRecord> save(@Valid @RequestBody UserRecord userDTO) {
        UserRecord savedUser = userService.save(userDTO, RoleType.ROLE_ADMIN);
        if  (savedUser  ==  null) {
            return ResponseEntity.badRequest().build();
        } else {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserRecord> update(@PathVariable Long userId, @RequestBody UserRecord userDTO) {
        UserRecord updatedUser = userService.update(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
