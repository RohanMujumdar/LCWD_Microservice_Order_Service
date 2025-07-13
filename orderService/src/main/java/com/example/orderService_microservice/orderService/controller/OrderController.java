package com.example.orderService_microservice.orderService.controller;

import com.example.orderService_microservice.orderService.dto.OrderDto;
import com.example.orderService_microservice.orderService.dto.external_restaurantService.FoodItemDto;
import com.example.orderService_microservice.orderService.entity.Order;
import com.example.orderService_microservice.orderService.entity.OrderStatus;
import com.example.orderService_microservice.orderService.service.OrderService;
import com.example.orderService_microservice.orderService.service.OrderServiceEventDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("micro/api/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderServiceEventDriver serviceEventDriver;

    // ✅ Create a new order
    @PostMapping("/")
    public Order createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @PostMapping("/event")
    public Order createOrderEventDriven(@RequestBody OrderDto orderDto)
    {
        return serviceEventDriver.createOrder(orderDto);
    }

    // ✅ Get order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    // Inside OrderController.java

    @GetMapping("/menu/{id}")
    public List<FoodItemDto> getMenuByRestaurant(@PathVariable String id) {
        return orderService.getMenuRestaurant(id);
    }


    // ✅ Get all orders for a specific customer
    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable String customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    // ✅ Update order status (e.g., PLACED → DELIVERED)
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable String id, @RequestParam OrderStatus status) {
        return orderService.updateOrderStatus(id, status);
    }

    // ✅ Delete/cancel an order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
    }
}
