package com.example.orderService_microservice.orderService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import com.example.orderService_microservice.orderService.dto.OrderDto;
import com.example.orderService_microservice.orderService.entity.Order;
import com.example.orderService_microservice.orderService.entity.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;


@Service
@Slf4j
public class OrderServiceEventDriver {

    @Autowired
    private StreamBridge streamBridge;


    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCustomerId(orderDto.getCustomerId());
        order.setItems(orderDto.getItems());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setStatus(OrderStatus.PENDING);
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setPaymentMode(orderDto.getPaymentMode());

        OrderDto dto = OrderDto.builder()
                .id(order.getId())
                .status(order.getStatus())
                .paymentMode(order.getPaymentMode())
                .build();

        // Assuming dto is already built
        Message<OrderDto> message = MessageBuilder.withPayload(dto).build();

        streamBridge.send("orderCreated-out-0", message);
        log.info("Order created");

        return order;

    }

}
