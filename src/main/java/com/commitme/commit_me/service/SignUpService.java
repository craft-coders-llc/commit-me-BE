package com.commitme.commit_me.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
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

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final SignUpRepository signUpRepository;

    public SignUpService (SignUpRepository signUpRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.signUpRepository = signUpRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> createSignup(SignUp signup, Integer id, Integer eventId, Integer userId) {
        Optional<Event> eventOptional = eventRepository.findById(userId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(!eventOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        if(!userOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        signup.setEvent(eventOptional.get());
        signup.setUser(userOptional.get());
        
        return new ResponseEntity<>(signUpRepository.save(signup), HttpStatus.CREATED);

    }

    public List<SignUp> getAllSignUps() {
        return this.signUpRepository.findAll();
    }
}
