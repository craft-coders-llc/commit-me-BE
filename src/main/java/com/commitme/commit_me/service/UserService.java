package com.commitme.commit_me.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.commitme.commit_me.exceptions.EmailAlreadyExistsException;
import com.commitme.commit_me.model.User;
import com.commitme.commit_me.repository.UserRepository;

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


}
