package com.example.orderService_microservice.orderService.entity;


import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderItem {

    private Long menuItemId;      // ID of the food item
    private Integer quantity;     // Quantity ordered
    private Double pricePerUnit;

}
