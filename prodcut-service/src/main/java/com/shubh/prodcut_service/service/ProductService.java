package com.shubh.prodcut_service.service;

import org.springframework.stereotype.Service;

import com.shubh.prodcut_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
}
