package com.commitme.commit_me.service;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.commitme.commit_me.model.Event;
import com.commitme.commit_me.model.SignUp;
import com.commitme.commit_me.model.User;
import com.commitme.commit_me.repository.EventRepository;
import com.commitme.commit_me.repository.SignUpRepository;
import com.commitme.commit_me.repository.UserRepository;

@Service
public class SignUpService {

    private final SignUpRepository signUpRepository;
    private final UserRepository userRepository;
    private EventRepository eventRepository;

    public SignUpService (SignUpRepository signUpRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.signUpRepository = signUpRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public ResponseEntity<Object> createSignUp(Integer userId, SignUp signUp, Integer eventId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (!userOptional.isPresent() && !eventOptional.isPresent()) {
            return ResponseEntity.notFound().build();
    }

    // signUp.setUser()
}
