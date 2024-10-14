package com.shubh.order_service.service;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.shubh.order_service.client.InventoryClient;
import com.shubh.order_service.dto.OrderRequest;
import com.shubh.order_service.event.OrderPlacedEvent;
import com.shubh.order_service.model.Order;
import com.shubh.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String ,OrderPlacedEvent> kafkaTemplate;
    
    public void placeOrder(OrderRequest orderRequest){

       var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());

        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

        orderRepository.save(order);

        // send the message to kafka Topic
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
        orderPlacedEvent.setOrderNumber(order.getOrderNumber());
        orderPlacedEvent.setEmail(orderRequest.userDetails().email());
        orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
        orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());
        log.info("Start - Sending OrderPlacedEvent {} to kafka topic order-placed",orderPlacedEvent);
        kafkaTemplate.send("order-placed",orderPlacedEvent);
        log.info("End - Sending OrderPlacedEvent {} to kafka topic order-placed", orderPlacedEvent);


        }else{
            throw new RuntimeException("product with SkuCode " + orderRequest.skuCode() + " is not in stock");
        }
        

    }
}
