package com.shubh.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "inventory", url = "http://localhost:8082")
public interface InventoryClient {
    
}
