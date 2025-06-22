package com.example.orderService_microservice.orderService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "order_service")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

public class Order {

    @Id
    private String id;  // UUID or custom format

    private String customerId;

    @ElementCollection
    @CollectionTable(name = "microservice_order_items", joinColumns = @JoinColumn(name = "id"))
    private List<OrderItem> items;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode = PaymentMode.UPI;

}
