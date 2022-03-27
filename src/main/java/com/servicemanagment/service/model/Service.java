package com.servicemanagment.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
public @Data class Service {
    @Id
    private String Id;
    List<ServicedItem> servicedItems;
    @NonNull
    String serviceStatus;
    @NonNull
    String orderDate;
    @NonNull
    String arrivalDate;
    @NonNull
    String returnDate;
    @NonNull
    boolean paidFor;
}
