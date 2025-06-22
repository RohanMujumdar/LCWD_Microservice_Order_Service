
// FoodItemDto.java
package com.example.orderService_microservice.orderService.dto.external_restaurantService;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDto {
    private String id;
    private String title;
    private String description;
    private int quantity;
    private boolean outOfStock;
    private FoodType foodType;
    private String restaurantId;
    private String foodCategoryId;
    private FoodCategoryDto foodCategoryDto;
}
