package com.servicemanagment.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
public @Data class Service {
    List<ServicedItem> servicedItems;
    String serviceStatus;
    String orderDate;
    String arrivalDate;
    String returnDate;
    boolean paidFor;
}
