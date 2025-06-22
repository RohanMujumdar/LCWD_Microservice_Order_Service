package com.example.orderService_microservice.orderService.service.external_restaurantService;

import com.example.orderService_microservice.orderService.config.AppConstants;
import com.example.orderService_microservice.orderService.dto.external_restaurantService.FoodItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class RestWebClientService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<FoodItemDto> getAllFoodItemsFromRestaurant(String id)
    {
        return webClientBuilder.baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build()
                .get()
                .uri("micro/api/restaurants/food-items/{id}", id)
                .retrieve()
                .bodyToFlux(FoodItemDto.class)
                .collectList()
                .block();
    }
}
