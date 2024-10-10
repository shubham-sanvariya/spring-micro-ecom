package com.shubh.prodcut_service.service;

import org.springframework.stereotype.Service;

import com.shubh.prodcut_service.dto.ProductRequest;
import com.shubh.prodcut_service.model.Product;
import com.shubh.prodcut_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        log.info("Product created successfully");
        return productRepository.save(product);
    }
}
