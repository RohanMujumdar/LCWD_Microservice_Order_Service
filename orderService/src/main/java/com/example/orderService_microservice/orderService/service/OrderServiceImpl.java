package com.example.orderService_microservice.orderService.service;

import com.example.orderService_microservice.orderService.dto.OrderDto;
import com.example.orderService_microservice.orderService.dto.external_restaurantService.FoodItemDto;
import com.example.orderService_microservice.orderService.dto.external_restaurantService.FoodType;
import com.example.orderService_microservice.orderService.entity.Order;
import com.example.orderService_microservice.orderService.entity.OrderStatus;
import com.example.orderService_microservice.orderService.repository.OrderRepository;
import com.example.orderService_microservice.orderService.service.external_restaurantService.RestWebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestWebClientService restWebClientService;


    @Override
    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCustomerId(orderDto.getCustomerId());
        order.setItems(orderDto.getItems());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setStatus(OrderStatus.PENDING);
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setPaymentMode(orderDto.getPaymentMode());

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order updateOrderStatus(String id, OrderStatus newStatus) {
        Order order = getOrderById(id);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<FoodItemDto> getMenuRestaurant(String id) {

        List<FoodItemDto> foodItemDtoList = restWebClientService.getAllFoodItemsFromRestaurant(id);
        return foodItemDtoList;
    }
}
