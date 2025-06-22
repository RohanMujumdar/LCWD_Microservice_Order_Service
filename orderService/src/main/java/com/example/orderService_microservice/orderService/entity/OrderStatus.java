package com.example.orderService_microservice.orderService.entity;

public enum OrderStatus {
    PENDING,
    PLACED,
    PREPARING,
    READY,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}
