package br.com.fiap.usermanager.service;

import br.com.fiap.usermanager.controller.exception.ControllerNotFoundException;
import br.com.fiap.usermanager.dto.UserRecord;
import br.com.fiap.usermanager.enums.RoleType;
import br.com.fiap.usermanager.model.Role;
import br.com.fiap.usermanager.model.UserLogin;
import br.com.fiap.usermanager.repository.RoleRepository;
import br.com.fiap.usermanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private RoleRepository roleRepository;

    public UserRecord findById(UUID userId) {
        UserLogin user = userRepository.findById(userId)
                .orElseThrow(() -> new ControllerNotFoundException("Usuario nao encontrado"));
        return toDTO(user);
    }

    public UserRecord save(UserRecord userDTO, RoleType roleType) {
        if(this.userRepository.findByLogin(userDTO.login()) != null)
            return null;

        List<Role> roles = roleRepository.findAll();
        roles = roles.stream()
                .filter(role -> role.getRoleName().equals(roleType))
                .toList();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        UserLogin newUser = new UserLogin(userDTO.login(),
                userDTO.name(), userDTO.email(),
                encryptedPassword, roles);

        newUser = this.userRepository.save(newUser);

        return toDTO(newUser);
    }

    public UserRecord update(UUID userId, UserRecord userDTO) {
        try {
            UserLogin user = userRepository.getReferenceById(userId);
            user.setName((userDTO.name()));
            user.setEmail(userDTO.email());
            user = userRepository.save(user);
            return toDTO(user);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuario nao encontrado");
        }
    }

    public void delete (UUID userId) {
        userRepository.deleteById(userId);
    }

    private UserRecord toDTO(UserLogin userLogin) {
        return new UserRecord(
                userLogin.getLogin(),
                userLogin.getName(),
                userLogin.getEmail(),
                ""
        );
    }

    private UserLogin toModel(UserRecord userRecord) {
        return new UserLogin(
                userRecord.login(),
                userRecord.name(),
                userRecord.email(),
                userRecord.password(),
                null
        );
    }
}