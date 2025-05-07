package com.commitme.commit_me.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.commitme.commit_me.exceptions.EmailAlreadyExistsException;
import com.commitme.commit_me.model.User;
import com.commitme.commit_me.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service

public class UserService {

    private final UserRepository userRepository;
    // private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//usar constructor
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
//crear un metodo de obtener eventos del usuario
//string
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
        // existingUser.setPassword(encoder.encode(updateUser.getPassword()));
        existingUser.setImagePath(updateUser.getImagePath());
        userRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);

    }

    @Transactional
    public ResponseEntity<Object> deleteUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User user = userOptional.get();
        userRepository.deleteById(user.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se ha eliminado correctamente");
    }


}
