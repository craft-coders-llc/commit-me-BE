package com.commitme.commit_me.service;

import org.springframework.stereotype.Service;

import com.commitme.commit_me.repository.UserRepository;

@Service

public class UserService {

    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
