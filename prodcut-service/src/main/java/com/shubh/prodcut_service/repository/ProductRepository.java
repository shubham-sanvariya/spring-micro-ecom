package com.shubh.prodcut_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shubh.prodcut_service.model.Product;

public interface ProductRepository extends MongoRepository<Product,String >{
    
}
