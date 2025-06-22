package com.example.orderService_microservice.orderService.service;

import com.example.orderService_microservice.orderService.dto.OrderDto;
import com.example.orderService_microservice.orderService.dto.external_restaurantService.FoodItemDto;
import com.example.orderService_microservice.orderService.entity.Order;
import com.example.orderService_microservice.orderService.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderDto orderDto);

    Order getOrderById(String id);

    List<Order> getOrdersByCustomerId(String customerId);

    Order updateOrderStatus(String id, OrderStatus newStatus);

    void deleteOrder(String id);

    List<FoodItemDto> getMenuRestaurant(String name);
}
