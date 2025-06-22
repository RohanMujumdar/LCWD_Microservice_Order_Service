// FoodCategoryDto.java
package com.example.orderService_microservice.orderService.dto.external_restaurantService;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCategoryDto {
    private String id;
    private String name;
    private String description;
    private List<FoodItemDto> foodItems;
}
