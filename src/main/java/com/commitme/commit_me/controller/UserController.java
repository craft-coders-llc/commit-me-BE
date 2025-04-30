package com.commitme.commit_me.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commitme.commit_me.model.User;
import com.commitme.commit_me.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/register")
public class UserController {
     private final UserService userService;
    
     public UserController(UserService userService) {
    this.userService = userService;

  }

  @PostMapping
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    return userService.createUser(user);
}
}