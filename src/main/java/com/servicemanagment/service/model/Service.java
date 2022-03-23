package com.servicemanagment.service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
public @Data class Service {
    @Id
    private String Id;
    List<ServicedItem> servicedItems;
    String serviceStatus;
    String orderDate;
    String arrivalDate;
    String returnDate;
    boolean paidFor;
}
