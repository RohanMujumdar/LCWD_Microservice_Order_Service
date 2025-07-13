package com.example.orderService_microservice.orderService.dto;

import com.example.orderService_microservice.orderService.entity.OrderItem;
import com.example.orderService_microservice.orderService.entity.OrderStatus;
import com.example.orderService_microservice.orderService.entity.PaymentMode;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private String id;
    private String customerId;
    private List<OrderItem> items;
    private Double totalAmount;
    private OrderStatus status;
    private String deliveryAddress;
    private PaymentMode paymentMode = PaymentMode.UPI;

}
