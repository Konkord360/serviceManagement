package com.servicemanagment.service.controller;

import com.servicemanagment.service.model.Service;
import com.servicemanagment.service.model.User;
import com.servicemanagment.service.model.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
            if(service.getServicedItems() == null)
                service.setServicedItems(new ArrayList<>());

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
    public ResponseEntity<User> modifyService(@RequestParam String userLogin, @RequestParam String serviceId, @RequestBody Service service) {
        Optional<User> optionalUser = userRepository.findByLogin(userLogin);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Service> optionalService = user.getService(serviceId);
            if(optionalService.isPresent()) {
               Service userService = optionalService.get();
               int serviceIndex = user.getServices().indexOf(userService);

               user.getServices().remove(serviceIndex);
               user.getServices().add(serviceIndex, service);
               userRepository.save(user);

               return ResponseEntity.ok(user);
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();

//        return (ResponseEntity<User>) user.map((foundUser) -> {
//            List<Service> serviceToBeDeleted = foundUser.getServices().stream().filter((serviceFiltered) -> serviceFiltered.getId().equals(serviceId)).toList();
//
//            if(serviceToBeDeleted.isEmpty())
//                return ResponseEntity.notFound().build();
//
//            int index = foundUser.getServices().indexOf(serviceToBeDeleted.get(0));
//            foundUser.getServices().remove(index);
//            foundUser.getServices().add(index, service);
//            userRepository.save(foundUser);
//            return ResponseEntity.ok(foundUser);
//        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteService")
    public ResponseEntity<User> removeService(@RequestParam String userLogin, @RequestParam String serviceId) {
        Optional<User> user = userRepository.findByLogin(userLogin);
        if (user.isPresent()) {
            Optional<Service> optionalService = user.get().getService(serviceId);
            if (optionalService.isPresent()) {
                user.get().getServices().remove(optionalService.get());
                userRepository.save(user.get());
                return ResponseEntity.ok(user.get());
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}
