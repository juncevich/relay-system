package com.relay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.relay.model.User;
import com.relay.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllRelays() {

        return userService.findAll();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {

        userService.deleteById(id);
    }
}
