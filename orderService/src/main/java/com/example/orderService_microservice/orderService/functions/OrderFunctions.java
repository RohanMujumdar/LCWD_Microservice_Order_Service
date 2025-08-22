package com.example.orderService_microservice.orderService.functions;

import com.example.orderService_microservice.orderService.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class OrderFunctions {

    @Bean
    public Supplier<String> test()
    {
        return ()->"This is testing, Hello World!, How are you?";
    }

    @Bean
    public Function<String, String> test2()
    {
        return String::toUpperCase;
    }


    @Bean
    public Supplier<String> collegeDays()
    {
        return ()->"I miss college";
    }

    @Bean
    public Function<OrderDto, String> createOrder()
    {
        return orderDto -> {
            System.out.println("Creating Order");
            System.out.println(orderDto.getId());
            System.out.println(orderDto.getTotalAmount());
            System.out.println(orderDto.getStatus());
            System.out.println(orderDto.getPaymentMode());

            return "order created with Id: " + orderDto.getId();
        };
    }

    @Bean
    public Consumer<Message<String>> orderAcknowledgementReceived()
    {

        return stringMessage -> {
            String str = stringMessage.getPayload();
            log.info("Entered receiving method");
            log.info("Message Recieved from PaymentDeliveryService: "+str);
        };
    }
}
