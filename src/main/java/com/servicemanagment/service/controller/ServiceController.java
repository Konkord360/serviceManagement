package com.servicemanagment.service.controller;

import com.servicemanagment.service.model.Service;
import com.servicemanagment.service.model.User;
import com.servicemanagment.service.model.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {

    final UserRepository userRepository;

    @Autowired
    public ServiceController(UserRepository userRepository) {
       this.userRepository = userRepository;
    }

    @PostMapping("/addService")
    public ResponseEntity<User> addService(@RequestParam String userLogin, @RequestBody Service service) {
       Optional<User> user = userRepository.findByLogin(userLogin);
       return user.map((foundUser) -> {
            service.setId(ObjectId.get().toString());
          foundUser.addService(service);
          userRepository.save(foundUser);
          return ResponseEntity.ok(foundUser);
       }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getService")
    public ResponseEntity<String> getService() {

        return ResponseEntity.ok("");
    }

    @GetMapping("/getServices")
    public ResponseEntity<String> getServices() {

        return ResponseEntity.ok("");
    }

    @PatchMapping("/modifyService")
    public ResponseEntity<String> modifyService() {
       return ResponseEntity.ok("");
    }

    @DeleteMapping("/deleteService")
    public ResponseEntity<String> removeService() {
       return ResponseEntity.ok("");
    }
}
