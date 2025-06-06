package com.commitme.commit_me.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.commitme.commit_me.exceptions.EmailAlreadyExistsException;
import com.commitme.commit_me.exceptions.InvalidCredentialsException;
import com.commitme.commit_me.model.User;
import com.commitme.commit_me.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyExistsException(
                    "(!) ERROR: Ya existe un usuario cadastrado con ese correo. Intente con otro.");
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public ResponseEntity<Object> getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<Object> updateUser(Integer id, User updateUser) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = userOptional.get();

        existingUser.setUsername(updateUser.getUsername());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setPassword(updateUser.getPassword());
        existingUser.setImagePath(updateUser.getImagePath());
        userRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }

    @Transactional
    public ResponseEntity<Object> deleteUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("(!) ERROR: No existe un usuario con el ID elegido");
        }
        User user = userOptional.get();
        userRepository.deleteById(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("El usuario se ha eliminado correctamente");
    }

    public User authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new InvalidCredentialsException("username o contraseña incorrecto(s)"));
    }
}


