package com.commitme.commit_me.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.commitme.commit_me.model.User;
import com.commitme.commit_me.service.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/v1/usuarios")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    return userService.createUser(user);
  }

  @GetMapping("/all")
  public List<User> getAllUsers () {
      return userService.getAllUsers();
  }
  

  @GetMapping("/{id}")

  public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
    return this.userService.getUserById(id);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Object> updateUser(@PathVariable Integer id, @RequestParam User updateUser) {
    return this.userService.updateUser(id, updateUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
    return this.userService.deleteUser(id);
  }

}