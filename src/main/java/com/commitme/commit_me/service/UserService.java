package com.commitme.commit_me.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.commitme.commit_me.model.User;
import com.commitme.commit_me.repository.UserRepository;

@Service

public class UserService {

    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<Object> createUser(User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }


}
