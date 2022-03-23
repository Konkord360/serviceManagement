package com.servicemanagment.service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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
        if(this.services == null)
            this.services = new ArrayList<>();

        if(!this.services.contains(service))
            this.services.add(service);
    }
}
