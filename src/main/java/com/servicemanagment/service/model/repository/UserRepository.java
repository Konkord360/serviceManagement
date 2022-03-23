package com.servicemanagment.service.model.repository;

import com.servicemanagment.service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
