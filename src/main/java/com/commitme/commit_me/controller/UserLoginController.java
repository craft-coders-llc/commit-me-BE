package com.commitme.commit_me.controller;
import com.commitme.commit_me.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commitme.commit_me.dto.LoginRequest;
import com.commitme.commit_me.exceptions.InvalidCredentialsException;
import com.commitme.commit_me.model.User;

@RestController
@RequestMapping("/api/v1/auth")
public class UserLoginController {

    private final UserService userService;

    UserLoginController(UserService userService) {
        this.userService = userService;
    }
    
    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    //     return userService.authenticate(request.getUsername(), request.getPassword())
    //             .map(user -> ResponseEntity.ok("Login exitoso del usuario " + user.getUsername()))
    //             .orElseGet(() -> ResponseEntity.status(401).body("(!) ERROR: username o contraseña incorrecto(s)"));
    // }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    try {
        User user = userService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Login exitoso del usuario " + user.getUsername());
    } catch (InvalidCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + e.getMessage());
    }
}

}
