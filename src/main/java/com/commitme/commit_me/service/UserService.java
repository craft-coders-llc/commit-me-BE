package com.commitme.commit_me.service;

import org.springframework.stereotype.Service;

@Service

public class UserService {

    
    private final UserService userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
