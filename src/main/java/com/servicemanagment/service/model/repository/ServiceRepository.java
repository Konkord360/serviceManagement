package com.servicemanagment.service.model.repository;


import com.servicemanagment.service.model.Service;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<Service, Integer> {
//    Optional<Service> findBy
}
