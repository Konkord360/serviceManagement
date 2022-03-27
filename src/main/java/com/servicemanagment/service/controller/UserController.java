package com.servicemanagment.service.controller;

import com.servicemanagment.service.model.User;
import com.servicemanagment.service.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if(user.getServices() == null)
            user.setServices(new ArrayList<>());

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam String login) {
        Optional<User> user = userRepository.findByLogin(login);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestParam String login) {
        Optional<User> user = userRepository.findByLogin(login);

        return user.map((presentUser) -> {
            userRepository.delete(presentUser);
            return ResponseEntity.ok(presentUser);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
