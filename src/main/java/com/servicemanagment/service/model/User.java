package com.servicemanagment.service.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

@Document(collection="Users")
@AllArgsConstructor
public @Data class User {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    String login;
    String password;
    String email;
    String phoneNumber;
    List<Service> services;

    public void addService(Service service) {
        if(!this.services.contains(service))
            this.services.add(service);
    }

    public Optional<Service> getService(String id) {
        List<Service> services = this.services.stream().filter(service -> service.getId().equals(id)).toList();
        if(services == null)
            return Optional.empty();

        return Optional.of(services.get(0));
    }
}
