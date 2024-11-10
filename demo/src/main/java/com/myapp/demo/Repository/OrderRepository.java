package com.myapp.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myapp.demo.Model.OrderRequest;

public interface OrderRepository extends MongoRepository<OrderRequest, Integer> {

}
