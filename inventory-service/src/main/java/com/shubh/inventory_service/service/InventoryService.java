package com.shubh.inventory_service.service;

import org.springframework.stereotype.Service;

import com.shubh.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    
}
