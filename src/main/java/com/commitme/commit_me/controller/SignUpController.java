package com.commitme.commit_me.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commitme.commit_me.model.SignUp;
import com.commitme.commit_me.service.SignUpService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/signups")
public class SignUpController {
    
    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/{userID}/{eventID}")
    public ResponseEntity<Object> createSignUp(@PathVariable Integer userID, Integer eventID, @Valid @RequestBody SignUp signup) {
        userID = signup.getUser().getId();
        eventID = signup.getEvent().getId();
        return signUpService.createSignup(signup, eventID, eventID, userID);
    }
    

}

